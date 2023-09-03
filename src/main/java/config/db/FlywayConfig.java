package config.db;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import utils.PropertiesUtil;

import static utils.constants.DatabaseConstants.*;
import static utils.constants.FlywayConstants.MIGRATION_LOCATION;

/**
 * Класс, для конфигурации миграций
 */
public class FlywayConfig {
    public Configuration config(){
        var config = new FluentConfiguration();
        config.dataSource(
                PropertiesUtil.getPropertyByKey(URL),
                PropertiesUtil.getPropertyByKey(USERNAME),
                PropertiesUtil.getPropertyByKey(PASSWORD)
        );
        config.driver(PropertiesUtil.getPropertyByKey(DRIVER));
        config.schemas(PropertiesUtil.getPropertyByKey(SCHEMA));
        config.locations(PropertiesUtil.getPropertyByKey(MIGRATION_LOCATION));

        return config;
    }

    public Flyway flywayTemplate(){
        return new Flyway(config());
    }

}
