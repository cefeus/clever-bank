package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String accFrom;
    private String accTo;
    private BigDecimal amount;

    public AccountDto(String accFrom, BigDecimal amount) {
        this.accFrom = accFrom;
        this.accTo = accFrom;
        this.amount = amount;
    }
}
