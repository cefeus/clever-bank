package model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(of = "number")
public class Account {
    private String number;
    private UUID ownerId;
    private BigDecimal balance;
    private Long bankId;
}
