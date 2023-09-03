package service;

import dto.StatementDto;
/**
 * Интерфейс для декларации необходимых методов для формирования выписки
 */
public interface StatementService {
    void createTransactionStatement(StatementDto statementDto);
}
