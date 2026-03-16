package com.g9.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Informations de connexion a la base de donnees
    private static final String URL = "jdbc:mysql://localhost:3306/planning_g9?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "@yoniE26koul18";
    // Instance unique de la connexion (Pattern Singleton)
    private static Connection connection = null;

    // Constructeur prive pour empecher l'instanciation de la classe
    private DatabaseConnection() {
    }

    // Methode principale pour recuperer la connexion
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Chargement du driver MySQL (Securite supplementaire)
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Etablissement de la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                
            } catch (ClassNotFoundException e) {
                throw new SQLException("Le driver MySQL est introuvable. Verifiez votre fichier pom.xml.", e);
            }
        }
        return connection;
    }

    // Methode pour fermer proprement la connexion a la fin du programme
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}

