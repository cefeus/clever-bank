package service.impl;

import model.Transaction;
import repository.impl.TransactionRepoImpl;
import service.TransactionService;

import java.sql.SQLException;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepoImpl transactionRepo = new TransactionRepoImpl();

    @Override
    public int saveTransaction(Transaction transaction) throws SQLException {
        return transactionRepo.saveTransaction(transaction);
    }
}
