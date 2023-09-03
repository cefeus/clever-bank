package model;

import lombok.*;
/**
 * Класс, который иллюстрирует сущность "Банк" в БД
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bank {
    private Long id;
    private String name;
}
