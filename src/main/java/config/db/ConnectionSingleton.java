package config.db;

/**
 * Создание синглтона соединения с БД
 */
public class ConnectionSingleton {
    private static volatile C3p0Configuration connection;

    private ConnectionSingleton() {
    }

    public static C3p0Configuration getConnection() {
        if (connection == null) {
            synchronized (ConnectionSingleton.class) {
                if (connection == null)
                    connection = new C3p0Configuration();
            }
        }
        return connection;
    }
}
