package service;

import model.Transaction;

import java.sql.SQLException;

public interface TransactionService {
    int saveTransaction(Transaction transaction) throws SQLException;
}
