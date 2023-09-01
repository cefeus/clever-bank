package repository;

import model.Transaction;

import java.sql.SQLException;

public interface TransactionRepo {

    int saveTransaction(Transaction transaction) throws SQLException;
}
