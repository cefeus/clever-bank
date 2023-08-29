package service;

import dto.AccountDto;

public interface AccountService {
    void deposit(AccountDto accDto);

    void withdraw(AccountDto accDto);

    void transfer(AccountDto accDto);

}
