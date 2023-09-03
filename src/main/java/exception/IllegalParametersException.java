package exception;
/**
 * Кастомное исключение, которое пробрасывается, если введены неверные параметры
 */
public class IllegalParametersException extends RuntimeException{
    public IllegalParametersException(String message) {
        super(message);
    }
}
