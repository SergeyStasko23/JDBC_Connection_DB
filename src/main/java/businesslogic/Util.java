package businesslogic;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public Connection getConnection() {
        // Получение данных из config.properties для подключения к БД.
        Properties property = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ERROR: config.properties is not exist!");
        }
        final String host = property.getProperty("db.host");
        final String login = property.getProperty("db.login");
        final String password = property.getProperty("db.password");

        // Подключение к БД
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(host, login, password);
            if(connection != null) {
                System.out.println("Connection OK");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
