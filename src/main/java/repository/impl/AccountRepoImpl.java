package repository.impl;

import config.db.ConnectionSingleton;
import dto.StatementDto;
import model.Account;
import org.apache.commons.lang3.StringUtils;
import utils.constants.SqlQueryConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static utils.constants.SqlQueryConstants.*;

public class AccountRepoImpl {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<StatementDto> getModifiedAccForStatement(String number) {

        try (var statement = connection.prepareStatement(SQL_GET_STATEMENT_INFO_BY_NUMBER)) {
            statement.setString(1, number);
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(buildStatement(rs, number))
                        : Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<Account> findAccByNumber(String number) {
        try (var statement = connection.prepareStatement(
                SQL_GET_ACC_BY_NUMBER,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
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

    public String getBankByAcc(String acc) throws SQLException {
        ResultSet rs;
        PreparedStatement ps;
        Connection conn = ConnectionSingleton.getConnection().open();
        ps = conn.prepareStatement(SqlQueryConstants.SQL_GET_BANK_NAME_BY_ACC_NUMBER);
        ps.setString(1, acc);
        rs = ps.executeQuery();
        String bankFrom = StringUtils.EMPTY;
        rs.next();
        return rs.getString("name");
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

    private StatementDto buildStatement(ResultSet rs, String number) throws SQLException {
        return StatementDto.builder()
                .owner(rs.getString("owner"))
                .creation_date(rs.getTimestamp("creation_date"))
                .balance(rs.getBigDecimal("balance"))
                .bankName(rs.getString("name"))
                .number(number)
                .build();
    }
}
