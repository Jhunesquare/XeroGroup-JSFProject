package com.example.xerogroup.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static final String url = "jdbc:postgresql://localhost:5432/turismo"; //Nombre de su base de datos
    private static final String username = "postgres";

    private ConnectionDatabase() {}

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(url, username, "junior07");//Contraseña postgres
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
