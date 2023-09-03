package repository;

import model.Account;

import java.sql.SQLException;
import java.util.Optional;

public interface AccountRepo {
    Optional<Account> findAccByNumber(String number);
    int updateAccBalance(Account acc) throws SQLException;
    void accrueInterest();
}