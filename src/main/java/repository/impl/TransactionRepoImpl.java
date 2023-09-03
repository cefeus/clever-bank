package repository.impl;

import config.db.ConnectionSingleton;
import dto.StatementDto;
import model.Transaction;
import model.TransactionType;
import repository.TransactionRepo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static utils.constants.SqlQueryConstants.*;
import static utils.constants.SqlQueryConstants.SQL_GET_ALL_TRANSFER_TRANSACTIONS_TO;

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
            statement.setString(4, String.valueOf(transaction.getType()));
            statement.setBigDecimal(5, transaction.getAmount());
            statement.setTimestamp(6, transaction.getCreatedAt());
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Transaction> findAllTransactions(StatementDto dto) {
        try (var statement = connection.prepareStatement(
                SQL_GET_ALL_TRANSACTION,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, dto.getAccNumber());
            statement.setString(2, dto.getAccNumber());
            statement.setDate(3, Date.valueOf(dto.getDateFrom()));
            statement.setDate(4, Date.valueOf(dto.getDateTo()));
            try (var rs = statement.executeQuery()) {
                List<Transaction> transactions = new LinkedList<>();
                while (rs.next()) {
                    transactions.add(buildTransaction(rs));
                }
                return transactions;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //TODO: Optional
    @Override
    public Map<UUID, String> findAllTransferTransactionsFrom(String number) {
        try (var statement = connection.prepareStatement(
                SQL_GET_ALL_TRANSFER_TRANSACTIONS_FROM,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, number);
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



    //TODO: Optional
    @Override
    public Map<UUID, String> findAllTransferTransactionsTo(String number) {
        try (var statement = connection.prepareStatement(
                SQL_GET_ALL_TRANSFER_TRANSACTIONS_TO,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, number);
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

    private Transaction buildTransaction(ResultSet rs) throws SQLException {
        return Transaction.builder()
                .id(UUID.fromString(rs.getString("id")))
                .accFrom(rs.getString("acc_from"))
                .accTo(rs.getString("acc_to"))
                .createdAt(rs.getTimestamp("created_at"))
                .amount(rs.getBigDecimal("amount"))
                .type(TransactionType.valueOf(rs.getString("transaction_type")))
                .build();
    }




}
