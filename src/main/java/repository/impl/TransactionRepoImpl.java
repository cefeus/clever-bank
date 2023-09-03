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
/**
 * Класс, который взаимодействует с таблицей "Transactions"
 */
public class TransactionRepoImpl implements TransactionRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод для сохранения сущности "Транзакция" в БД
     * @param transaction - сущность "Транзакция"
     * @return int для проверки выполнения запроса
     * @throws SQLException
     */
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

    /**
     * метод для нахождения всех транзакций с участием передаваемого номера счета
     * и в определенном временном интервалле
     * @param dto - сущность, содержащая временной интервалл и номер счета
     * @return список транзакций
     */
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

    /**
     * метод для нахождения всех "TRANSFER" транзакций, где номер счета выступает в качестве отправителя
     * @param number - номер счета
     * @return Map<UUID, String>, где ключ - uuid транзакции,  значение - ФИО отправителя
     */
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

    /**
     * метод для нахождения всех "TRANSFER" транзакций, где номер счета выступает в качестве получателя
     * @param number - номер счета
     * @return Map<UUID, String>, где ключ - uuid транзакции,  значение - ФИО получателя
     */
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

    /**
     * метод получения общей суммы прихода средств для соответствующего номера счета
     * и в определенном временном интервалле
     * @param dto - сущность, содержащая временной интервалл и номер счета
     * @return если сумма была найдена - Optional<String>
     *     в противном случае Optional.empty()
     */
    @Override
    public Optional<String> getIncome(StatementDto dto) {
        try (var statement = connection.prepareStatement(
                SQL_GET_INCOME,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, dto.getAccNumber());
            statement.setDate(2, Date.valueOf(dto.getDateFrom()));
            statement.setDate(3, Date.valueOf(dto.getDateTo()));
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(rs.getString("uhod"))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод получения общей суммы ухода средств для соответствующего номера счета
     * и в определенном временном интервалле
     * @param dto - сущность, содержащая временной интервалл и номер счета
     * @return если сумма была найдена - Optional<String>
     *     в противном случае Optional.empty()
     */
    @Override
    public Optional<String> getOutCome(StatementDto dto) {
        try (var statement = connection.prepareStatement(
                SQL_GET_OUTCOME,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setString(1, dto.getAccNumber());
            statement.setDate(2, Date.valueOf(dto.getDateFrom()));
            statement.setDate(3, Date.valueOf(dto.getDateTo()));
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(rs.getString("prihod"))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * метод для создания сущности "Транзакция", путем извлечения нужных параметров из ResultSet
     * @param rs - ResultSet
     * @return сущность "Транзакция"
     * @throws SQLException
     */
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
