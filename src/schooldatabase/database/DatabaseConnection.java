package schooldatabase.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    private final static String URL = "jdbc:postgresql://localhost/million";
    // private final static String URL =
    // "jdbc:postgresql://ec2-3-221-177-27.compute-1.amazonaws.com:5432/d76soo9vsp11us";
    // static String username = "qmvxoxndwomhft";
    // static String password =
    // "2a8705453f1db32195e33e794c6d92676092eef84fbfd5065911757eebd7bf54";

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                // connection = DriverManager.getConnection(URL, username, password);

                connection = DriverManager.getConnection(URL);
                // connection = DriverManager.getConnection(URL);
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
