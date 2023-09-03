package repository;

import model.Account;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Интерфейс для декларации необходимых методов для работы с сущностью "Счет"
 */
public interface AccountRepo {
    Optional<Account> findAccByNumber(String number);
    int updateAccBalance(Account acc) throws SQLException;
    void accrueInterest();
}