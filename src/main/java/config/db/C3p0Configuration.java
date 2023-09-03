package config.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import utils.PropertiesUtil;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import static utils.constants.DatabaseConstants.*;

/**
 * Конфигурационный класс для пула соединений
 */
public class C3p0Configuration {
    private final ComboPooledDataSource connPool;

    public C3p0Configuration() {
        connPool = new ComboPooledDataSource();
        loadProperties();
    }

    /**
     * загружает необходимые параметры для пула соединений
     */
    private void loadProperties() {
        connPool.setJdbcUrl(PropertiesUtil.getPropertyByKey(URL));
        connPool.setUser(PropertiesUtil.getPropertyByKey(USERNAME));
        connPool.setPassword(PropertiesUtil.getPropertyByKey(PASSWORD));
        try {
            connPool.setDriverClass(PropertiesUtil.getPropertyByKey(DRIVER));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод, который открывает соединение
     * @return Connection
     * @throws SQLException
     */
    public Connection open() throws SQLException {
        return connPool.getConnection();
    }

    public void close() {
        connPool.close();
    }
}
