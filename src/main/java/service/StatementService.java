package service;

import dto.StatementDto;

public interface StatementService {
    void createTransactionStatement(StatementDto statementDto);
}
