package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class StatementDto {
    private String owner;
    private Timestamp creation_date;
    private BigDecimal balance;
    private String bankName;
    private String number;

}
