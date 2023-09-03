package repository;

import model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo {
    Optional<User> findUserById(UUID id);
}
