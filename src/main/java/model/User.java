package model;

import lombok.*;

import java.util.UUID;
/**
 * Класс, который иллюстрирует сущность "Пользователь" в БД
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private UUID id;
    private String name;
}
