package repository.impl;

import config.db.ConnectionSingleton;
import dto.TransferTransactionDto;
import model.Transaction;
import repository.TransactionRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static utils.constants.SqlQueryConstants.SQL_GET_USER_BY_ID;
import static utils.constants.SqlQueryConstants.SQL_SAVE_TRANSACTION;

public class TransactionRepoImpl implements TransactionRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public Map<UUID, String> findAllTransferTransactions() {
        try (var statement = connection.prepareStatement(
                SQL_GET_ALL_TRANSFER_TRANSACTIONS,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            try (var rs = statement.executeQuery()) {
                Map<UUID, String> map = new HashMap<>();
                while (rs.next()) {
                    map.put(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("name")
                    );
                }
                return map;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
