package repository;

import dto.TransferTransactionDto;
import model.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TransactionRepo {
    int saveTransaction(Transaction transaction) throws SQLException;

    Map<UUID, String> findAllTransferTransactions();
}
