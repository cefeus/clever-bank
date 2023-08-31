package service.impl;

import model.Transaction;
import repository.TransactionRepo;
import service.TransactionService;

import java.sql.SQLException;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo = new TransactionRepo();

    @Override
    public int saveTransaction(Transaction transaction) throws SQLException {
        return transactionRepo.saveTransaction(transaction);
    }
}
