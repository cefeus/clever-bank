package service.impl;

import model.Transaction;
import service.TransactionService;

import java.sql.Connection;
import java.sql.SQLException;

import static utils.constants.SqlQueryConstants.SQL_SAVE_TRANSACTION;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public int saveTransaction(Transaction transaction, Connection connection) throws SQLException {
        var preparedStatement = connection.prepareStatement(SQL_SAVE_TRANSACTION);
        preparedStatement.setString(1, transaction.getAccFrom());
        preparedStatement.setString(2, transaction.getAccTo());
        preparedStatement.setString(3, transaction.getType());
        preparedStatement.setBigDecimal(4, transaction.getAmount());
        preparedStatement.setTimestamp(5, transaction.getCreatedAt());
        return preparedStatement.executeUpdate();
    }
}
