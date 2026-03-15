package com.g9.service;

import com.g9.model.Planning;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations CRUD sur les cours.
 * Travail réalisé par Juvenal.
 */
public class CourseService {
    private final List<Planning> courses = new ArrayList<>();
    private int nextId = 1;

    /**
     * Ajoute un cours après vérification des conflits.
     * 
     * @throws IllegalArgumentException si un conflit est détecté.
     */
    public void addCourse(Planning course) {
        List<String> conflicts = ConflictChecker.findConflicts(course, courses);
        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("Impossible d'ajouter le cours : " + String.join(", ", conflicts));
        }
        course.setId(nextId++);
        courses.add(course);
    }

    /**
     * Retourne la liste de tous les cours.
     */
    public List<Planning> getAllCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Supprime un cours par son ID.
     */
    public boolean deleteCourse(int id) {
        return courses.removeIf(c -> c.getId() == id);
    }

    /**
     * Met à jour un cours existant.
     */
    public void updateCourse(int id, Planning updatedCourse) {
        for (int i = 0; i < courses.size(); i++) {
            Planning c = courses.get(i);
            if (c.getId() == id) {
                // Pour l'update, on vérifie les conflits avec les AUTRES cours
                List<Planning> otherCourses = new ArrayList<>(courses);
                otherCourses.remove(i);

                List<String> conflicts = ConflictChecker.findConflicts(updatedCourse, otherCourses);
                if (!conflicts.isEmpty()) {
                    throw new IllegalArgumentException("Mise à jour impossible : " + String.join(", ", conflicts));
                }

                updatedCourse.setId(id);
                courses.set(i, updatedCourse);
                return;
            }
        }
        throw new IllegalArgumentException("Cours avec l'ID " + id + " non trouvé.");
    }

    /**
     * Recherche un cours par son ID.
     */
    public Optional<Planning> getCourseById(int id) {
        return courses.stream().filter(c -> c.getId() == id).findFirst();
    }
}
