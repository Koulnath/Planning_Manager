package com.planning;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConflictChecker {
    
    /**
     * Vérifie si deux plannings sont en conflit (même jour et heures qui se chevauchent)
     * @param p1 Premier planning
     * @param p2 Deuxième planning
     * @return true si conflit, false sinon
     */
    public static boolean checkConflict(Planning p1, Planning p2) {
        // Validation des entrées
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Les plannings ne peuvent pas être null");
        }

        // Si les jours sont différents → pas de conflit
        if (p1.getJour() != p2.getJour()) {
            return false;
        }

        // Vérifie si les heures se chevauchent strictement
        // Pas de conflit si les heures se touchent exactement (ex: 10h-12h et 12h-14h)
        return p1.getHeureDebut().isBefore(p2.getHeureFin()) && 
               p2.getHeureDebut().isBefore(p1.getHeureFin());
    }

    /**
     * Version non-statique pour la rétrocompatibilité
     */
    public boolean checkConflictNonStatic(Planning p1, Planning p2) {
        return checkConflict(p1, p2);
    }

    /**
     * Trouve tous les conflits dans une liste
     * @param plannings Liste des plannings à vérifier
     * @return Liste des descriptions de conflits
     */
    public static List<String> findAllConflicts(List<Planning> plannings) {
        if (plannings == null || plannings.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> conflits = new ArrayList<>();
        
        for (int i = 0; i < plannings.size(); i++) {
            Planning p1 = plannings.get(i);
            if (p1 == null) continue;

            for (int j = i + 1; j < plannings.size(); j++) {
                Planning p2 = plannings.get(j);
                if (p2 == null) continue;

                if (checkConflict(p1, p2)) {
                    conflits.add(String.format(
                        "Conflit entre [%s] %s %s-%s et [%s] %s %s-%s",
                        p1.getDescription(),
                        p1.getJour(),
                        p1.getHeureDebut(),
                        p1.getHeureFin(),
                        p2.getDescription(),
                        p2.getJour(),
                        p2.getHeureDebut(),
                        p2.getHeureFin()
                    ));
                }
            }
        }
        
        return conflits;
    }
}
