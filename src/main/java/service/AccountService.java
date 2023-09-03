package service;

import dto.AccountDto;
/**
 * Интерфейс для декларации необходимых методов для работы с сущностью "Счет"
 */
public interface AccountService {
    void deposit(AccountDto accDto);

    void withdraw(AccountDto accDto);

    void transfer(AccountDto accDto);

}
