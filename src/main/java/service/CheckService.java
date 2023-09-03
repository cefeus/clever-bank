package service;

import model.Transaction;
/**
 * Интерфейс для декларации необходимых методов для формирования чека
 */
public interface CheckService {
    void formCheck(Transaction transaction);
}
