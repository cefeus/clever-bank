package exception;

public class CustomDateTimeParseException extends RuntimeException{
    public CustomDateTimeParseException(String message) {
        super(message);
    }
}
