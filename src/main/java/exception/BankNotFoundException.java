package exception;
/**
 * Кастомное исключение, которое пробрасывается, если банк не был найден
 */
public class BankNotFoundException extends RuntimeException {

    public BankNotFoundException(String message) {
        super(message);
    }
}
