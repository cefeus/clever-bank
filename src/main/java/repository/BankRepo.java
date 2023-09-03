package repository;

import model.Bank;

import java.util.Optional;
/**
 * Интерфейс для декларации необходимых методов для работы с сущностью "Банк"
 */
public interface BankRepo {
    Optional<Bank> findBankByAccNumber(String number);

    Optional<Bank> findBankById(Long id);
}
