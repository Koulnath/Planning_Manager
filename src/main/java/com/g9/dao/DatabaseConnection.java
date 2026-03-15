package com.g9.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton gérant la connexion à la base de données MySQL.
 * Travail à finaliser par Sokeng.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tp207";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // À configurer

    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion à la base de données réussie.");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Pilote MySQL non trouvé.");
                throw new SQLException(e);
            }
        }
        return connection;
    }
}
