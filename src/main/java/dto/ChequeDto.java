package dto;

import model.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ChequeDto {
    private String from;
    private String to;
    private String type;
    private Timestamp createdAt;
    private BigDecimal amount;
    private String BankFrom;
    private String BankTo;

    public ChequeDto(Transaction transaction, String BankFrom, String BankTo) {
        this.from = transaction.getAccFrom();
        this.to = transaction.getAccTo();
        this.type = transaction.getType();
        this.createdAt = transaction.getCreatedAt();
        this.amount = transaction.getAmount();
        this.BankFrom = BankFrom;
        this.BankTo = BankTo;
    }
}
