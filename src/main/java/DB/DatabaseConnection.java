package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/Test";
                final String user = "postgres";
                final String password = "postgres";
                connection = DriverManager.getConnection(DB_URL, user, password);
                System.out.println("Установлено соединение");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
