package com.anya.eventboard.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String DB_URL = System.getenv("db_url");
    public static final String DB_USERNAME = System.getenv("db_username");
    public static final String DB_PASSWORD = System.getenv("db_password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

}
