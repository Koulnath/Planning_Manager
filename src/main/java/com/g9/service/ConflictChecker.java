package com.g9.service;

import com.g9.model.Planning;
import java.util.List;

public class ConflictChecker {

    /**
     * Vérifie si le nouveau cours entre en conflit avec les cours existants.
     * Lance une IllegalArgumentException si un conflit est détecté.
     */
    public static void valider(Planning nouveauCours, List<Planning> coursExistants) {
        for (Planning existant : coursExistants) {
            
            // On vérifie d'abord si les cours ont lieu le même jour
            if (existant.getJourSemaine() == nouveauCours.getJourSemaine()) {
                
                // Formule mathématique pour vérifier le chevauchement de deux plages horaires
                // (Debut A < Fin B) ET (Fin A > Debut B)
                boolean chevauchement = nouveauCours.getHeureDebut().isBefore(existant.getHeureFin()) &&
                                        nouveauCours.getHeureFin().isAfter(existant.getHeureDebut());
                                        
                if (chevauchement) {
                    
                    // Règle métier 1 : Un professeur ne peut pas enseigner deux cours en même temps
                    if (existant.getNomProf().equalsIgnoreCase(nouveauCours.getNomProf())) {
                        throw new IllegalArgumentException("Conflit détecté : Le professeur " + nouveauCours.getNomProf() + 
                                                           " a déjà le cours '" + existant.getNomCours() + "' à cette heure.");
                    }
                    
                    // Règle métier 2 : Une salle ne peut pas accueillir deux cours en même temps
                    if (existant.getNomSalle().equalsIgnoreCase(nouveauCours.getNomSalle())) {
                        throw new IllegalArgumentException("Conflit détecté : La salle " + nouveauCours.getNomSalle() + 
                                                           " est déjà occupée par le cours '" + existant.getNomCours() + "'.");
                    }
                }
            }
        }
    }
}