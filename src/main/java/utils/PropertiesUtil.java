package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * класс для работы с yaml файлом
 */
public class PropertiesUtil {
    private static final Properties PROP = new Properties();

    static {
        loadProperties();
    }

    /**
     * метод для выгрузки yaml файла
     */
    private static void loadProperties() {
        try (InputStream input = PropertiesUtil.class.
                getClassLoader().
                getResourceAsStream("application.yml")) {
            PROP.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод для получения проперти по ключу
     * @param key
     * @return - Проперти
     */
    public static String getPropertyByKey(String key) {
        return PROP.getProperty(key);
    }
}
