package repository;

import config.db.ConnectionSingleton;
import dto.AccountDto;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountRepo {
    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean update(AccountDto accDto){

    }


}
