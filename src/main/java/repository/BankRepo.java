package repository;

import model.Bank;

import java.util.Optional;

public interface BankRepo {
    Optional<Bank> findBankByAccNumber(String number);

    Optional<Bank> findBankById(Long id);
}
