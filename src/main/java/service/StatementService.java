package service;

import java.time.LocalDate;

public interface StatementService {

    public void createTransactionStatement(LocalDate start, LocalDate end, String number);
}
