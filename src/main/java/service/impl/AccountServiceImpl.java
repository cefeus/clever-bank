package service.impl;

import config.db.ConnectionSingleton;
import dto.AccountDto;
import lombok.val;
import model.Transaction;
import model.TransactionType;
import service.AccountService;
import service.TransactionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

import static utils.constants.SqlQueryConstants.*;

public class AccountServiceImpl implements AccountService {

    private final TransactionService transactionService = new TransactionServiceImpl();
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
            var preparedStatement = connection.prepareStatement(SQL_UPDATE_ACC_DEPOSIT);
            preparedStatement.setBigDecimal(1, accDto.getAmount());
            preparedStatement.setString(2, accDto.getAccTo());

            if (preparedStatement.executeUpdate() == 1) System.out.println("Средства зачислены");
            else System.out.println("Счет не найден");

            val transaction = buildTransaction(accDto, TransactionType.DEPOSIT);

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

            preparedStatement = connection.prepareStatement(SQL_UPDATE_ACC_WITHDRAW);
            preparedStatement.setBigDecimal(1, accDto.getAmount());
            preparedStatement.setString(2, accDto.getAccFrom());
            preparedStatement.executeUpdate();

            val transaction = buildTransaction(accDto, TransactionType.WITHDRAW);

            if (isTransfer && transactionService.saveTransaction(transaction, connection) == 1)
                System.out.println("Транзакция сохранена\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void transfer(AccountDto accDto) {

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

