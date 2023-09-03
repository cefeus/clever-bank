package repository.impl;

import config.db.ConnectionSingleton;
import model.Account;
import repository.AccountRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static utils.constants.SqlQueryConstants.*;

/**
 * Класс, который взаимодействует с таблицей "Accounts"
 */
public class AccountRepoImpl implements AccountRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод, который находит сущность счет по номеру счета
     * @param number - номер счета
     * @return в случае, если счет был найден - возвращает Optional<Account>
     *     в противном - Optional.empty()
     */
    @Override
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

    /**
     * обновляет баланс сущности "Счет"
     * @param acc - сущность "Счет", с ранее сформированным балансом
     * @return int для проверки выполнения запроса
     * @throws SQLException
     */
    @Override
    public int updateAccBalance(Account acc) throws SQLException {
        try (var statement = connection.prepareStatement(SQL_UPDATE_ACC_BALANCE)) {
            statement.setBigDecimal(1, acc.getBalance());
            statement.setString(2, acc.getNumber());
            return statement.executeUpdate();
        }
    }

    /**
     * метод начисления процентов
     */
    @Override
    public void accrueInterest() {
        try (var statement = connection.prepareStatement(SQL_ACCRUE_PERCENT)) {
            if (statement.executeUpdate() != 0) System.out.println("Проценты начислены\n");
            else System.out.println("Начисление не требуется\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод для создания сущности "Счет", путем извлечения нужных параметров из ResultSet
     * @param rs - ResultSet
     * @return сущность "Счет"
     * @throws SQLException
     */
    private Account buildAcc(ResultSet rs) throws SQLException {
        return Account.builder()
                .number(rs.getString("number"))
                .ownerId(UUID.fromString(rs.getString("owner_id")))
                .balance(rs.getBigDecimal("balance"))
                .bankId(rs.getLong("bank_id"))
                .createdAt(rs.getTimestamp("creation_date").toLocalDateTime())
                .build();
    }
}
