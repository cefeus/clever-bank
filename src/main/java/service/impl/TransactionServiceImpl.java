package service.impl;

import model.Transaction;
import repository.impl.TransactionRepoImpl;
import service.TransactionService;

import java.sql.SQLException;
/**
 * Класс для манипуляций с сущностью Транзакция
 */
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepoImpl transactionRepoImpl = new TransactionRepoImpl();

    /**
     * метод для сохранения сущности Транзакция в БД
     * @param transaction - сущность Транзакция
     * @return int для проверки выполнения метода
     * @throws SQLException
     */
    @Override
    public int saveTransaction(Transaction transaction) throws SQLException {
        return transactionRepoImpl.saveTransaction(transaction);
    }
}
