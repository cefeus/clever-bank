package service.impl;

import config.db.ConnectionSingleton;
import dto.AccountDto;
import lombok.val;
import model.Transaction;
import model.TransactionType;
import service.AccountService;
import service.TransactionService;
import utils.TransactionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static utils.constants.SqlQueryConstants.*;

public class AccountServiceImpl implements AccountService {

    private final TransactionService transactionService = new TransactionServiceImpl();
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private boolean isTransfer = false;
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deposit(AccountDto accDto) {
        try {
            lock1.lock();
            var preparedStatement = connection.prepareStatement(SQL_UPDATE_ACC_DEPOSIT);
            preparedStatement.setBigDecimal(1, accDto.getAmount());
            preparedStatement.setString(2, accDto.getAccTo());

            if (preparedStatement.executeUpdate() == 1) System.out.println("Средства зачислены");
            else System.out.println("Счет не найден");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock1.unlock();
        }

        val transaction = buildTransaction(accDto, TransactionType.DEPOSIT);

        try {
            if (isTransfer && transactionService.saveTransaction(transaction, connection) == 1)
                System.out.println("Транзакция сохранена\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void withdraw(AccountDto accDto) {
        try {
            var preparedStatement = connection.prepareStatement(SQL_GET_BALANCE_BY_NUMBER);
            preparedStatement.setString(1, accDto.getAccFrom());
            ResultSet re = preparedStatement.executeQuery();

            if (re.next()) {
                if (accDto.getAmount().compareTo(re.getBigDecimal("balance")) > 0) {
                    System.out.println("Недостаточно средств на счете");
                    return;
                }
            } else {
                System.out.println("Счет не найден");
                return;
            }

            lock2.lock();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ACC_WITHDRAW);
            preparedStatement.setBigDecimal(1, accDto.getAmount());
            preparedStatement.setString(2, accDto.getAccFrom());
            preparedStatement.executeUpdate();

            val transaction = buildTransaction(accDto, TransactionType.WITHDRAW);

            if (isTransfer && transactionService.saveTransaction(transaction, connection) == 1)
                System.out.println("Транзакция сохранена\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock2.unlock();
        }
    }

    @Override
    public void transfer(AccountDto accDto) {
        try {
            lock1.lock();
            lock2.lock();
            isTransfer = true;
            TransactionManager.startTransaction(connection);
            withdraw(accDto);
            deposit(accDto);
            TransactionManager.commitTransaction(connection);

        } catch (SQLException e) {
            try {
                TransactionManager.rollbackTransaction(connection);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            isTransfer = false;
            lock1.unlock();
            lock2.unlock();
        }

        val transaction = buildTransaction(accDto, TransactionType.TRANSFER);

        try {
            if (transactionService.saveTransaction(transaction, connection) == 1)
                System.out.println("Транзакция сохранена\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Transaction buildTransaction(AccountDto accDto, TransactionType type) {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .accFrom(accDto.getAccFrom())
                .accTo(accDto.getAccTo())
                .type(type.toString())
                .amount(accDto.getAmount())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
