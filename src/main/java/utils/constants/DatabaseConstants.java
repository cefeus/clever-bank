package utils.constants;

import lombok.experimental.UtilityClass;
/**
 * Класс для хранения параметров для БД
 * (выступает посредником между .yaml и PropertiesUtil)
 */
@UtilityClass
public class DatabaseConstants {
    public static String URL = "URL";
    public static String USERNAME = "USERNAME";
    public static String PASSWORD = "PASSWORD";
    public static String DRIVER = "DRIVER";
    public static String SCHEMA = "SCHEMA";
}
