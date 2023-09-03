package exception;

/**
 * Кастомное исключение, которое пробрасывается, если счет не был найден
 */
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
