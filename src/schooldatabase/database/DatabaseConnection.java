package schooldatabase.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Use the ConfigReader to read configuration
                ConfigReader configReader = new ConfigReader("app.config");

                String url = configReader.getUrl();
                String username = configReader.getUsername();
                String password = configReader.getPassword();

                // Load the database driver
                Class.forName("org.postgresql.Driver");
                // Establish the connection
                connection = DriverManager.getConnection(url, username, password);

                System.out.println("Database connection successfully established.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Database connection failed.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}
