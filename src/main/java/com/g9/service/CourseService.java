package com.g9.service;

import com.g9.model.Planning;
import com.g9.dao.CourseDAO;
import com.g9.dao.CourseDAOImpl;
import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations CRUD sur les cours.
 * Travail réalisé par Juvenal.
 * Désormais lié à la couche DAO pour la persistance.
 */
public class CourseService {
    private final CourseDAO courseDAO;

    public CourseService() {
        // Dans un vrai projet, on utiliserait de l'injection de dépendances
        this.courseDAO = new CourseDAOImpl();
    }

    public CourseService(CourseDAO dao) {
        this.courseDAO = dao;
    }

    /**
     * Ajoute un cours après vérification des conflits.
     * 
     * @throws IllegalArgumentException si un conflit est détecté.
     */
    public void addCourse(Planning course) {
        List<String> conflicts = ConflictChecker.findConflicts(course, courseDAO.findAll());
        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("Impossible d'ajouter le cours : " + String.join(", ", conflicts));
        }
        courseDAO.save(course);
    }

    /**
     * Retourne la liste de tous les cours.
     */
    public List<Planning> getAllCourses() {
        return courseDAO.findAll();
    }

    /**
     * Supprime un cours par son ID.
     */
    public boolean deleteCourse(int id) {
        if (getCourseById(id).isPresent()) {
            courseDAO.delete(id);
            return true;
        }
        return false;
    }

    /**
     * Met à jour un cours existant.
     */
    public void updateCourse(int id, Planning updatedCourse) {
        Optional<Planning> existing = courseDAO.findById(id);
        if (existing.isPresent()) {
            List<Planning> otherCourses = courseDAO.findAll();
            otherCourses.removeIf(c -> c.getId() == id);

            List<String> conflicts = ConflictChecker.findConflicts(updatedCourse, otherCourses);
            if (!conflicts.isEmpty()) {
                throw new IllegalArgumentException("Mise à jour impossible : " + String.join(", ", conflicts));
            }

            updatedCourse.setId(id);
            courseDAO.update(updatedCourse);
        } else {
            throw new IllegalArgumentException("Cours avec l'ID " + id + " non trouvé.");
        }
    }

    /**
     * Recherche un cours par son ID.
     */
    public Optional<Planning> getCourseById(int id) {
        return courseDAO.findById(id);
    }
}
