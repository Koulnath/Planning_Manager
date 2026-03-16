package com.g9.service;

import com.g9.dao.DatabaseConnection;
import com.g9.model.JourSemaine;
import com.g9.model.Planning;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseService {

    // Récupère les données dynamiquement depuis MySQL
    public List<Planning> getAllCourses() {
        List<Planning> liste = new ArrayList<>();
        String query = "SELECT * FROM planning";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Planning p = new Planning(
                    rs.getString("nom_cours"),
                    rs.getString("nom_prof"),
                    rs.getString("nom_salle"),
                    JourSemaine.valueOf(rs.getString("jour")),
                    rs.getString("heure_debut"),
                    rs.getString("heure_fin")
                );
                // Utilisation du nouveau nom de méthode : setId()
                p.setId(rs.getInt("id"));
                liste.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erreur de lecture BD : " + e.getMessage());
        }
        return liste;
    }

    // Ajoute un cours après avoir vérifié les conflits
    public void addCourse(Planning nouveauCours) {
        
        // 1. On récupère les cours actuels de la BD
        List<Planning> existants = getAllCourses();
        
        // 2. On délègue la vérification au Conflict Checker
        ConflictChecker.valider(nouveauCours, existants);

        // 3. Aucun conflit détecté, on insère dans MySQL
        String query = "INSERT INTO planning (nom_cours, nom_prof, nom_salle, jour, heure_debut, heure_fin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nouveauCours.getNomCours());
            pstmt.setString(2, nouveauCours.getNomProf());
            pstmt.setString(3, nouveauCours.getNomSalle());
            pstmt.setString(4, nouveauCours.getJourSemaine().name());
            pstmt.setTime(5, Time.valueOf(nouveauCours.getHeureDebut()));
            pstmt.setTime(6, Time.valueOf(nouveauCours.getHeureFin()));

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion en BD : " + e.getMessage());
        }
    }

    // Supprime physiquement le cours de MySQL
    public boolean deleteCourse(int idPlanning) {
        String query = "DELETE FROM planning WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idPlanning);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression en BD : " + e.getMessage());
            return false;
        }
    }

    public Optional<Planning> getCourseById(int idPlanning) {
        // Utilisation du nouveau nom de méthode : getId()
        return getAllCourses().stream()
                .filter(p -> p.getId() == idPlanning)
                .findFirst();
    }
}