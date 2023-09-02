package repository;

import config.db.ConnectionSingleton;
import model.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

import static utils.constants.SqlQueryConstants.SQL_SAVE_TRANSACTION;

public class TransactionRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int saveTransaction(Transaction transaction) throws SQLException {
        try (var statement = connection.prepareStatement(SQL_SAVE_TRANSACTION)) {
            statement.setObject(1, transaction.getId());
            statement.setString(2, transaction.getAccFrom());
            statement.setString(3, transaction.getAccTo());
            statement.setString(4, transaction.getType());
            statement.setBigDecimal(5, transaction.getAmount());
            statement.setTimestamp(6, transaction.getCreatedAt());
            return statement.executeUpdate();
        }
    }
}
