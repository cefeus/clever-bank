package service.impl;

import exception.AccountNotFoundException;
import repository.impl.AccountRepoImpl;
import service.StatementService;

import java.time.LocalDate;

public class StatementServiceImpl implements StatementService {

    private final AccountRepoImpl accountRepo = new AccountRepoImpl();
    private final PdfServiseImpl pdf = new PdfServiseImpl();

    @Override
    public void createTransactionStatement(LocalDate start, LocalDate end, String number) {
        try {
            var statement = accountRepo.getModifiedAccForStatement(number)
                    .orElseThrow(() -> new AccountNotFoundException("Account not found"));
            // TODO ALARM get info for statement table
            pdf.formStatement(statement, start, end);
        } catch (RuntimeException ex) {

        }
    }

    public void createMoneyStatement() {} //TODO ALARM
    // транзакции: дата, тип, от кого, сумма со знаком


}
