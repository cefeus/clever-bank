package repository.impl;

import config.db.ConnectionSingleton;
import model.Bank;
import repository.BankRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static utils.constants.SqlQueryConstants.SQL_GET_BANK_BY_ACC_NUMBER;
import static utils.constants.SqlQueryConstants.SQL_GET_BANK_BY_ID;

public class BankRepoImpl implements BankRepo {

    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Bank> findBankByAccNumber(String number) {
        try (var statement = connection.prepareStatement(SQL_GET_BANK_BY_ACC_NUMBER,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, number);
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(buildBank(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Bank> findBankById(Long id) {
        try (var statement = connection.prepareStatement(SQL_GET_BANK_BY_ID,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setLong(1, id);
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(buildBank(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Bank buildBank(ResultSet rs) throws SQLException {
        return Bank.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }
}
