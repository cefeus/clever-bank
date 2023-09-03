package service.impl;

import exception.BankNotFoundException;
import lombok.val;
import model.Bank;
import model.Transaction;
import org.apache.commons.lang3.StringUtils;
import repository.BankRepo;
import repository.impl.BankRepoImpl;
import service.CheckService;
import service.FileService;
import utils.constants.CheckConstants;

import java.time.LocalDateTime;

import static utils.constants.CheckConstants.CHECK_TEMPLATE_RU;
import static utils.constants.PatternConstants.DATE_TIME_PATH_PATTERN;
/**
 * Класс для формирования чека
 */
public class CheckServiceImpl implements CheckService {

    private final BankRepo bankRepo = new BankRepoImpl();
    private final FileService fileService = new FileServiceImpl();

    /**
     * метод формирования чека и печати в формате .txt
     * @param transaction - сущность Транзакция, на основании которой формируется чек
     */
    @Override
    public void formCheck(Transaction transaction) {
        val check = buildCheck(transaction);
        val path = String.format(
                CheckConstants.CHECK_PATH,
                LocalDateTime.now().format(DATE_TIME_PATH_PATTERN)
        );
        fileService.formTxt(path, check);
    }

    /**
     * метод формирования чека
     * @param transaction - сущность Транзакция, на основании которой формируется чек
     */
    private String buildCheck(Transaction transaction) {
        Bank bankFrom;
        Bank bankTo;
        val accFrom = transaction.getAccFrom();
        val accTo = transaction.getAccTo();

        if (StringUtils.equals(accFrom, accTo)) {
            bankTo = bankFrom = bankRepo.findBankByAccNumber(accFrom)
                    .orElseThrow(() -> new BankNotFoundException("Bank not found"));
        } else {
            bankFrom = bankRepo.findBankByAccNumber(accFrom)
                    .orElseThrow(() -> new BankNotFoundException("Bank not found"));
            bankTo = bankRepo.findBankByAccNumber(accFrom)
                    .orElseThrow(() -> new BankNotFoundException("Bank not found"));
        }
        return String.format(
                CHECK_TEMPLATE_RU,
                transaction.getId().toString(),
                transaction.getCreatedAt().toLocalDateTime(),
                transaction.getCreatedAt(),
                transaction.getType(),
                bankFrom.getName(),
                bankTo.getName(),
                accFrom,
                accTo,
                transaction.getAmount()
        );
    }
}