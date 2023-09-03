package repository.impl;

import config.db.ConnectionSingleton;
import model.User;
import repository.UserRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static utils.constants.SqlQueryConstants.SQL_GET_USER_BY_ID;
/**
 * Класс, который взаимодействует с таблицей "Users"
 */
public class UserRepoImpl implements UserRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод, который находит сущность пользователь по id
     * @param id - uuid пользователя
     * @return в случае, если пользователь был найден - возвращает Optional<User>
     *         в противном - Optional.empty()
     */
    @Override
    public Optional<User> findUserById(UUID id) {
        try (var statement = connection.prepareStatement(
                SQL_GET_USER_BY_ID,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            statement.setObject(1, id);
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(buildUser(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод для создания сущности "Пользователь", путем извлечения нужных параметров из ResultSet
     * @param rs - ResultSet
     * @return сущность "Пользователь"
     * @throws SQLException
     */
    private User buildUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .build();
    }
}
