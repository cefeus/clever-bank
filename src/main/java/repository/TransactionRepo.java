package repository;

import dto.StatementDto;
import model.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransactionRepo {
    int saveTransaction(Transaction transaction) throws SQLException;
    List<Transaction> findAllTransactions(StatementDto dto);
    Map<UUID, String> findAllTransferTransactionsTo(String number);
    Map<UUID, String> findAllTransferTransactionsFrom(String number);
}
