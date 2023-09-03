package exception;
/**
 * Кастомное исключение, которое пробрасывается, если транзакция не была найдена
 */
public class TransactionsNotFoundException extends RuntimeException{
    public TransactionsNotFoundException(String message) {
        super(message);
    }
}
