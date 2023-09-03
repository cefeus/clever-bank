package utils.constants;

import lombok.experimental.UtilityClass;
/**
 * Класс для хранения параметров для миграции
 * (выступает посредником между .yaml и PropertiesUtil)
 */
@UtilityClass
public class FlywayConstants {
    public static String MIGRATION_LOCATION = "MIGRATION_LOCATION";
}
