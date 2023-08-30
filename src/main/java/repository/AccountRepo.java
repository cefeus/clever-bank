package repository;

import config.db.ConnectionSingleton;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static utils.constants.SqlQueryConstants.SQL_GET_ACC_BY_NUMBER;
import static utils.constants.SqlQueryConstants.SQL_UPDATE_ACC_BALANCE;

public class AccountRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Account> findAccByNumber(String number) {
        try (var statement = connection.prepareStatement(
                SQL_GET_ACC_BY_NUMBER,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE))
        {
            statement.setString(1, number);
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(buildAcc(rs))
                        : Optional.empty();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public int updateAccBalance(Account acc) throws SQLException {
        try (var statement = connection.prepareStatement(SQL_UPDATE_ACC_BALANCE)) {
            statement.setBigDecimal(1, acc.getBalance());
            statement.setString(2, acc.getNumber());
            return statement.executeUpdate();
        }
    }

    private Account buildAcc(ResultSet rs) throws SQLException {
        return Account.builder()
                .number(rs.getString("number"))
                .ownerId(UUID.fromString(rs.getString("owner_id")))
                .balance(rs.getBigDecimal("balance"))
                .bankId(rs.getLong("bank_id"))
                .build();
    }
}
