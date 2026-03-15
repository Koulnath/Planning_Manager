package com.g9.service;

import com.g9.model.Planning;

public class ConflictChecker {

    /**
     * Vérifie si deux cours se chevauchent dans la même salle.
     * Logique : Il y a conflit si (Cours1.debut < Cours2.fin) ET (Cours2.debut <
     * Cours1.fin)
     * ET (Cours1.salle == Cours2.salle).
     * 
     * @param c1 Premier cours
     * @param c2 Deuxième cours
     * @return true s'il y a conflit, false sinon.
     */
    public static boolean hasConflict(Planning c1, Planning c2) {
        if (c1 == null || c2 == null) {
            return false;
        }

        // Vérification de la salle (sensible à la casse et aux espaces)
        if (c1.getSalle() == null || c2.getSalle() == null
                || !c1.getSalle().trim().equalsIgnoreCase(c2.getSalle().trim())) {
            return false;
        }

        // Logique de chevauchement temporel
        // Un conflit existe si l'intervalle [début1, fin1] intersecte [début2, fin2]
        return (c1.getHeureDebut() < c2.getHeureFin()) && (c2.getHeureDebut() < c1.getHeureFin());
    }
}
