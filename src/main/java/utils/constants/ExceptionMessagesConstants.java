package utils.constants;

import lombok.experimental.UtilityClass;
/**
 * Класс для хранения описания ошибок
 * (выступает посредником между .yaml и PropertiesUtil)
 */
@UtilityClass
public class ExceptionMessagesConstants {
    public static String INCORRECT_ACC_NUMBER = "INCORRECT_ACC_NUMBER";
    public static String INCORRECT_ACC_AMOUNT = "INCORRECT_ACC_AMOUNT";

}
