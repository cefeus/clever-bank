package service.impl;

import config.db.ConnectionSingleton;
import dto.AccountDto;
import exception.AccountNotFoundException;
import exception.ValidationException;
import lombok.val;
import model.Account;
import model.Transaction;
import model.TransactionType;
import repository.AccountRepo;
import service.AccountService;
import service.TransactionService;
import utils.TransactionManager;
import validator.AccountValidator;

import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static utils.constants.SqlQueryConstants.SQL_GET_BALANCE_BY_NUMBER;

public class AccountServiceImpl implements AccountService {
    private final TransactionManager transactionManager = new TransactionManager();
    private final AccountValidator accValidator = new AccountValidator();
    private final TransactionService transactionService = new TransactionServiceImpl();
    private final AccountRepo accountRepo = new AccountRepo();
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private boolean isTransfer = false;


    @Override
    public void deposit(AccountDto accDto) {
        try {
            accValidator.validate(accDto);
        } catch (ValidationException exception) {
            exception.getExceptionMessages()
                    .forEach(System.out::println);
            return;
        }

        try {
            lock1.lock();
            var storedAcc = accountRepo.findAccByNumber(accDto.getAccTo())
                    .orElseThrow(() -> new AccountNotFoundException("Account not found"));

            storedAcc.setBalance(storedAcc.getBalance().add(accDto.getAmount()));
            accountRepo.updateAccBalance(storedAcc);
            System.out.println("Средства зачислены");

            val transaction = buildTransaction(accDto, TransactionType.DEPOSIT);

            if (!isTransfer && transactionService.saveTransaction(transaction) == 1)
                System.out.println("Транзакция сохранена\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            lock1.unlock();
        }

    }

    @Override
    public void withdraw(AccountDto accDto) {
        try {
            accValidator.validate(accDto);
        } catch (ValidationException exception) {
            exception.getExceptionMessages()
                    .forEach(System.out::println);
            return;
        }

        try {
            lock2.lock();
            var storedAcc = accountRepo.findAccByNumber(accDto.getAccFrom())
                    .orElseThrow(() -> new AccountNotFoundException("Account not found"));

            if (accDto.getAmount().compareTo(storedAcc.getBalance()) > 0) {
                System.out.println("Недостаточно средств на счете");
                return;
            }
            storedAcc.setBalance(storedAcc.getBalance().subtract(accDto.getAmount()));
            accountRepo.updateAccBalance(storedAcc);

            val transaction = buildTransaction(accDto, TransactionType.WITHDRAW);

            if (!isTransfer && transactionService.saveTransaction(transaction) == 1)
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
            accValidator.validate(accDto);
        } catch (ValidationException exception) {
            exception.getExceptionMessages()
                    .forEach(System.out::println);
            return;
        }

        try {
            lock1.lock();
            lock2.lock();  //TODO ALARM
            isTransfer = true;
            transactionManager.startTransaction();
            withdraw(accDto);
            deposit(accDto);
            transactionManager.commitTransaction();

        } catch (RuntimeException | SQLException e) {
            try {
                transactionManager.rollbackTransaction();
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
            if (transactionService.saveTransaction(transaction) == 1)
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
