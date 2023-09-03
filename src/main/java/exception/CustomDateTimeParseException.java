package exception;
/**
 * Кастомное исключение, которое пробрасывается, если произошла ошибка при парсе даты
 */
public class CustomDateTimeParseException extends RuntimeException{
    public CustomDateTimeParseException(String message) {
        super(message);
    }
}
