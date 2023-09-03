package model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
/**
 * Класс, который иллюстрирует сущность "Транзакция" в БД
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Transaction {
    private UUID id;
    private String accFrom;
    private String accTo;
    private TransactionType type;
    private BigDecimal amount;
    private Timestamp createdAt;
}
