package dto;

import lombok.*;

import java.math.BigDecimal;


/**
 * Служит, в основном, как посредник между клиентом и сервисом
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
