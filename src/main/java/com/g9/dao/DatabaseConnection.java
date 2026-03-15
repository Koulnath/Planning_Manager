package com.g9.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Remplacer "root" et "" par les identifiants XAMPP/MySQL locaux si différent
    private static final String URL = "jdbc:mysql://localhost:3306/planning_g9?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Driver JDBC introuvable.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur : Impossible de se connecter à la base de données.");
            e.printStackTrace();
        }
        return connection;
    }
}