package model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Класс, который иллюстрирует сущность "Аккаунт" в БД
 */
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
    private LocalDateTime createdAt;
}
