package service;

import model.Transaction;

import java.sql.SQLException;
/**
 * Интерфейс для декларации необходимых методов для работы с сущностью "Транзакция"
 */
public interface TransactionService {
    int saveTransaction(Transaction transaction) throws SQLException;
}
