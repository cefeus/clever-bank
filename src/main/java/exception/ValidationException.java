package exception;

import lombok.Getter;

import java.util.Set;
/**
 * Кастомное исключение, которое пробрасывается, если валидация прошла не успешно
 */
@Getter
public class ValidationException extends RuntimeException {

    private Set<String> exceptionMessages;

    public ValidationException(Set<String> exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }
}
