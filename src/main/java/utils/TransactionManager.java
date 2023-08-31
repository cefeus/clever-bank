package utils;

import config.db.ConnectionSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransactionManager {
    private static Savepoint savepoint;

    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint();
    }

    public void commitTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void rollbackTransaction() throws SQLException {
        connection.rollback(savepoint);
    }
}
