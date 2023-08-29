package exception;

import lombok.Getter;

import java.util.List;
@Getter
public class ValidationException extends RuntimeException{

    private List<String> exceptionMessages;

    public ValidationException(List<String> exceptionMessages) {
        this.exceptionMessages = exceptionMessages;
    }
}
