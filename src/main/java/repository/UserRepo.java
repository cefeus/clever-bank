package repository;

import model.User;

import java.util.Optional;
import java.util.UUID;
/**
 * Интерфейс для декларации необходимых методов для работы с сущностью "Пользователь"
 */
public interface UserRepo {
    Optional<User> findUserById(UUID id);
}
