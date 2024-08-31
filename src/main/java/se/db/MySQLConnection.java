package se.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:33006/JDBC-Workshop";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
