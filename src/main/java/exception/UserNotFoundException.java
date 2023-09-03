package exception;
/**
 * Кастомное исключение, которое пробрасывается, если пользователь не был найден
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
