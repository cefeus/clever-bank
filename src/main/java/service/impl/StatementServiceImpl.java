package service.impl;

import dto.StatementDto;
import exception.AccountNotFoundException;
import exception.BankNotFoundException;
import exception.TransactionsNotFoundException;
import exception.UserNotFoundException;
import lombok.val;
import model.*;
import repository.AccountRepo;
import repository.BankRepo;
import repository.TransactionRepo;
import repository.UserRepo;
import repository.impl.AccountRepoImpl;
import repository.impl.BankRepoImpl;
import repository.impl.TransactionRepoImpl;
import repository.impl.UserRepoImpl;
import service.FileService;
import service.StatementService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static utils.constants.CheckConstants.*;
import static utils.constants.PatternConstants.*;

public class StatementServiceImpl implements StatementService {

    private AccountRepo accRepo = new AccountRepoImpl();
    private BankRepo bankRepo = new BankRepoImpl();
    private UserRepo userRepo = new UserRepoImpl();
    private TransactionRepo transactionRepo = new TransactionRepoImpl();
    private FileService fileService = new FileServiceImpl();

    @Override
    public void createTransactionStatement(StatementDto statementDto) {
        //validation
        val account = accRepo.findAccByNumber(statementDto.getAccNumber())
                .orElseThrow(() -> new AccountNotFoundException("Аккаунт не найден"));
        val user = userRepo.findUserById(account.getOwnerId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        val bank = bankRepo.findBankById(account.getBankId())
                .orElseThrow(() -> new BankNotFoundException("Банк не найден"));

        if (LocalDate.MAX.equals(statementDto.getDateFrom()))
            statementDto.setDateFrom(LocalDate.from(account.getCreatedAt()));

        var statement = buildStatement(statementDto, account, user, bank);

        fileService.formTxt(
                String.format(STATEMENT_PATH,
                        LocalDateTime.now().format(DATE_TIME_PATH_PATTERN)), statement);
    }

    private String buildStatement(StatementDto stmtDto, Account acc, User user, Bank bank) {
        return stmtDto.getType().statementAlgo.apply(this, stmtDto, acc, user, bank);
    }

    public String buildTransactionHistoryStatement(StatementDto stmtDto, Account acc, User user, Bank bank) {
        Map<UUID, String> allTransferTransactionsFrom = transactionRepo
                .findAllTransferTransactionsFrom(stmtDto.getAccNumber());
        Map<UUID, String> allTransferTransactionsTo = transactionRepo
                .findAllTransferTransactionsTo(stmtDto.getAccNumber());

        List<Transaction> transactions = transactionRepo.findAllTransactions(stmtDto);

        var transactionHistoryStatement = buildStatementHeader(stmtDto, acc, user, bank);

        transactionHistoryStatement.append(TRANSACTION_HISTORY_STATEMENT_TEMPLATE_BODY);

        addTransactionHistoryStatementFooter(
                transactionHistoryStatement,
                allTransferTransactionsFrom,
                allTransferTransactionsTo,
                transactions,
                stmtDto);

        return transactionHistoryStatement.toString();
    }

    public String buildMoneyFlowStatement(StatementDto stmtDto, Account acc, User user, Bank bank) {

        var transactionMoneyFlowStatement = buildStatementHeader(stmtDto, acc, user, bank);

        transactionMoneyFlowStatement.append(MONEY_FLOW_STATEMENT_TEMPLATE_BODY);

        val income = transactionRepo.getIncome(stmtDto)
                .orElseThrow(() -> new TransactionsNotFoundException("Транзакции не найдены"));

        val outcome = transactionRepo.getOutCome(stmtDto)
                .orElseThrow(() -> new TransactionsNotFoundException("Транзакции не найдены"));

        transactionMoneyFlowStatement
                .append(String.format(MONEY_FLOW_STATEMENT_TEMPLATE_FOOTER,
                        income,
                        outcome));

        return transactionMoneyFlowStatement.toString();
    }

    private StringBuilder buildStatementHeader(StatementDto stmtDto, Account acc, User user, Bank bank) {
        return new StringBuilder()
                .append(String.format(TRANSACTION_STATEMENT_TEMPLATE_HEADER,
                        bank.getName(),
                        user.getName(),
                        acc.getNumber(),
                        acc.getCreatedAt().format(DATE_PATTERN),
                        stmtDto.getDateFrom().format(DATE_PATTERN),
                        stmtDto.getDateTo().format(DATE_PATTERN),
                        LocalDateTime.now().format(DATE_TIME_PATTERN),
                        acc.getBalance()
                ));
    }

    private void addTransactionHistoryStatementFooter(StringBuilder transactionHistoryStatement,
                                                      Map<UUID, String> allTransferTransactionsFrom,
                                                      Map<UUID, String> allTransferTransactionsTo,
                                                      List<Transaction> transactions,
                                                      StatementDto stmtDto) {
        transactions.forEach(transaction -> {
            if (TransactionType.TRANSFER.equals(transaction.getType())) {
                if (transaction.getAccFrom().equals(stmtDto.getAccNumber())) {
                    // здесь логика если переводим мы
                    transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE_FOOTER,
                            transaction.getCreatedAt().toLocalDateTime().format(DATE_PATTERN),
                            TRANSFER_TO + allTransferTransactionsFrom.get(transaction.getId()),
                            "-" + transaction.getAmount()
                    ));
                } else {
                    //здесь логика если переводят нам
                    transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE_FOOTER,
                            transaction.getCreatedAt().toLocalDateTime().format(DATE_PATTERN),
                            TRANSFER_FROM + allTransferTransactionsTo.get(transaction.getId()),
                            transaction.getAmount()
                    ));
                }
            } else {
                //здесь логика депозит и виздро
                if (transaction.getType().equals(TransactionType.DEPOSIT))
                    transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE_FOOTER,
                            transaction.getCreatedAt().toLocalDateTime().format(DATE_PATTERN),
                            TransactionType.DEPOSIT.getAction(),
                            transaction.getAmount()
                    ));
                else {
                    transactionHistoryStatement.append(String.format(TRANSACTION_STATEMENT_TEMPLATE_FOOTER,
                            transaction.getCreatedAt().toLocalDateTime().format(DATE_PATTERN),
                            TransactionType.WITHDRAW.getAction(),
                            transaction.getAmount()
                    ));
                }

            }
            transactionHistoryStatement.append("\n");
        });
    }
}