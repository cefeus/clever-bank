package service.impl;

import exception.BankNotFoundException;
import lombok.val;
import model.Bank;
import model.Transaction;
import org.apache.commons.lang3.StringUtils;
import repository.BankRepo;
import repository.impl.BankRepoImpl;
import service.CheckService;
import service.PdfService;
import utils.constants.CheckConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.constants.CheckConstants.CHECK_TEMPLATE_ENG;

public class CheckServiceImpl implements CheckService {

    private final BankRepo bankRepo = new BankRepoImpl();
    private final PdfService pdfService = new PdfServiceImpl();

    @Override
    public void formCheck(Transaction transaction) {
        val check = buildCheck(transaction);
        DateTimeFormatter frmtr = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        val path = String.format(CheckConstants.CHECK_PATH, LocalDateTime.now().format(frmtr));
        pdfService.formPdf(check, path);
    }

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
                CHECK_TEMPLATE_ENG,
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
