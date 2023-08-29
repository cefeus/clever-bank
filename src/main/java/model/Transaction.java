package model;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction {
    private UUID id;
    private String fromAcc;
    private String toAcc;
    private String type;
    private Timestamp createdAt;
    private BigDecimal amount;
}
