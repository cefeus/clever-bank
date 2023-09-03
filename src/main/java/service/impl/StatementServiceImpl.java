package service.impl;

import dto.StatementDto;
import exception.AccountNotFoundException;
import exception.BankNotFoundException;
import exception.UserNotFoundException;
import lombok.val;
import model.*;
import org.apache.commons.lang3.EnumUtils;
import repository.AccountRepo;
import repository.BankRepo;
import repository.TransactionRepo;
import repository.UserRepo;
import repository.impl.AccountRepoImpl;
import repository.impl.BankRepoImpl;
import repository.impl.TransactionRepoImpl;
import repository.impl.UserRepoImpl;
import service.StatementService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static utils.constants.CheckConstants.TRANSACTION_STATEMENT_TEMPLATE;

public class StatementServiceImpl implements StatementService {

    private AccountRepo accRepo = new AccountRepoImpl();
    private BankRepo bankRepo = new BankRepoImpl();
    private UserRepo userRepo = new UserRepoImpl();
    private TransactionRepo transactionRepo = new TransactionRepoImpl();

    @Override
    public void createTransactionStatement(StatementDto statementDto) {
        //validation
        val account = accRepo.findAccByNumber(statementDto.getAccNumber())
                .orElseThrow(() -> new AccountNotFoundException("Аккаунт не найден"));
        val user = userRepo.findUserById(account.getOwnerId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        val bank = bankRepo.findBankById(account.getBankId())
                .orElseThrow(() -> new BankNotFoundException("Банк не найден"));

//        buildStatement(statementDto.getType(), statementDto, account, user, bank)
        var statement = buildStatement(statementDto, account, user, bank);


    }

    //    private String buildStatement(StatementDto stmtDto, Account account, User user, Bank bank){
//
//
//    }
    private String buildStatement(StatementDto stmtDto, Account acc, User user, Bank bank) {
        return stmtDto.getType().statementAlgo.apply(this, stmtDto, acc, user, bank);
    }

    public String buildTransactionHistoryStatement(StatementDto stmtDto, Account acc, User user, Bank bank) {
        Map<UUID, String> allTransferTransactions = transactionRepo.findAllTransferTransactions();
        List<Transaction> transactions = transactionRepo.findAllTransactions();
        StringBuilder transactionHistoryStatement = new StringBuilder();

        transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE,
                user.getName(),
                acc.getNumber(),
                acc.getCreatedAt(),
                stmtDto.getDateFrom(),
                stmtDto.getDateTo(),
                LocalDateTime.now(),
                acc.getBalance()
        ));

        transactions.forEach(transaction -> {
            if(allTransferTransactions.containsKey(transaction.getId())){
                transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE_FOOTER),
                        transaction.getCreatedAt(),
                        transaction.getType().getAction() + " от " + allTransferTransactions.get(transaction.getId()),
                        transaction.getAmount()
                        );
            } else {
                transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE_FOOTER),
                        transaction.getCreatedAt(),
                        transaction.getType().getAction(),
                        transaction.getAmount()
                );
            }
        });
        return transactionHistoryStatement.toString();
    }

    public String buildMoneyFlowStatement(StatementDto stmtDto, Account acc, User user, Bank bank) {
       //todo
    }
}

