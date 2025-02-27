package com.example.mynetworkapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myNetworkApp?useSSL=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Aa123456#";
    private static Connection conn = null;
    private DatabaseConnection() {}

    public static Connection getConnectionconnect() {
        try {
             conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("You are connected to database");
            return conn;
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();
            return conn;
        }
    }

    public static void main(String[] args) {
        getConnectionconnect();
    }
}
