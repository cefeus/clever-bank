package exception;

import lombok.Getter;

import java.util.Set;

@Getter
public class ValidationException extends RuntimeException {

    private Set<String> exceptionMessages;

    public ValidationException(Set<String> exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }
}
