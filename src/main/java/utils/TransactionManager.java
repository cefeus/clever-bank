package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

public class TransactionManager {
    private static Savepoint savepoint;

    public static void startTransaction(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        savepoint = connection.setSavepoint();
    }

    public static void commitTransaction(Connection connection) throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public static void rollbackTransaction(Connection connection) throws SQLException {
        connection.rollback(savepoint);
    }
}
