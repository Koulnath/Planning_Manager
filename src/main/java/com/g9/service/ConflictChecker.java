package com.g9.service;

import com.g9.model.Planning;
import java.util.ArrayList;
import java.util.List;

/**
 * Service de détection de conflits dans l'emploi du temps.
 */
public class ConflictChecker {

    /**
     * Vérifie si deux cours sont en conflit.
     * Un conflit existe si :
     * 1. Ils sont le même jour ET
     * 2. Leurs horaires se chevauchent ET
     * 3. (Ils sont dans la même SALLE OU ils ont le même ENSEIGNANT)
     */
    public static boolean hasConflict(Planning c1, Planning c2) {
        if (c1 == null || c2 == null)
            return false;

        // 1. Vérification du jour
        if (c1.getJour() != c2.getJour())
            return false;

        // 2. Vérification du chevauchement horaire (strict)
        boolean overlap = c1.getHeureDebut().isBefore(c2.getHeureFin()) &&
                c2.getHeureDebut().isBefore(c1.getHeureFin());

        if (!overlap)
            return false;

        // 3. Vérification de la ressource (Salle ou Enseignant)
        boolean sameSalle = c1.getSalle() != null && c1.getSalle().trim().equalsIgnoreCase(c2.getSalle().trim());
        boolean sameProf = c1.getEnseignant() != null
                && c1.getEnseignant().trim().equalsIgnoreCase(c2.getEnseignant().trim());

        return sameSalle || sameProf;
    }

    /**
     * Trouve tous les conflits pour un nouveau cours par rapport à une liste
     * existante.
     */
    public static List<String> findConflicts(Planning newCourse, List<Planning> existingCourses) {
        List<String> conflicts = new ArrayList<>();
        for (Planning existing : existingCourses) {
            if (hasConflict(newCourse, existing)) {
                String reason = existing.getSalle().equalsIgnoreCase(newCourse.getSalle()) ? "Salle" : "Enseignant";
                conflicts.add("Conflit de " + reason + " avec : " + existing.getNomCours());
            }
        }
        return conflicts;
    }
}
