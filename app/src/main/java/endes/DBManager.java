package endes;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static String url = "jdbc:postgresql://postgres:5432/db_dev";
    private static String user = "postgres";
    private static String password = "password";

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to " + url +
            " using user=" + user + " and password=" + password);
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;        
        } catch (Exception e) {
           
            if (e instanceof ClassNotFoundException) {
                System.out.println("PostgreSQL JDBC Driver not found.");
            } else if (e  instanceof java.sql.SQLException) {
                System.out.println("SQL Exception.");
            } else {
                System.out.println("Unknown Exception.");
            }
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }
}