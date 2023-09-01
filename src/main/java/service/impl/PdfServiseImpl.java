package service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import dto.ChequeDto;
import dto.StatementDto;
import model.Transaction;
import repository.impl.AccountRepoImpl;
import utils.constants.CheckConstants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class PdfServiseImpl {

    //TODO ALARM create interface

    private AccountRepoImpl repo = new AccountRepoImpl();
    private String path;
    private String output;
    private int id = 0;

    public void formStatement(StatementDto dto, LocalDate start, LocalDate end) {
        output = buildStatement(dto, start, end);
        path = String.format(CheckConstants.STATEMENT_PATH, new Timestamp(System.currentTimeMillis()));
        fileWriter(path, output);
    }

    public void formCheck(Transaction transaction) {
        output = buildCheque(transaction);
        path = String.format(CheckConstants.CHECK_PATH,new Timestamp(System.currentTimeMillis()));
        fileWriter(path, output);
    }
    private void fileWriter(String path, String text) {
        Document doc = new Document();
        Font font = FontFactory.getFont(CheckConstants.FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc.add(new Paragraph(text, font));
            doc.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private ChequeDto expand(Transaction transaction) {
        try {
            String bankFrom = repo.getBankByAcc(transaction.getAccFrom());
            String bankTo = repo.getBankByAcc(transaction.getAccTo());
            return new ChequeDto(transaction, bankFrom, bankTo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildCheque(Transaction transaction) {
        ChequeDto cheque = expand(transaction);
        return String.format(CheckConstants.CHEQUE_TEMPLATE,
                ++id,
                cheque.getCreatedAt().getTime(),
                cheque.getCreatedAt().toLocalDateTime(),
                cheque.getType(),
                cheque.getBankFrom(),
                cheque.getBankTo(),
                cheque.getFrom(),
                cheque.getTo(),
                cheque.getAmount());
    }

    private String buildStatement(StatementDto dto, LocalDate start, LocalDate end) {
        return String.format(CheckConstants.STATEMENT_TEMPLATE,
                dto.getBankName(),
                dto.getOwner(),
                dto.getNumber(),
                dto.getCreation_date(),
                start.toString(),
                end.toString(),
                new Timestamp(System.currentTimeMillis()),
                dto.getBalance()
        );
    }
}
