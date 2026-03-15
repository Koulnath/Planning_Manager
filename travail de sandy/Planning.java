package com.planning;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Planning {
    private JourSemaine jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String description;

    public Planning(JourSemaine jour, String heureDebut, String heureFin, String description) {
        // Validation des paramètres
        if (jour == null) {
            throw new IllegalArgumentException("Le jour ne peut pas être null");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La description ne peut pas être vide");
        }
        
        this.jour = jour;
        this.description = description.trim();
        
        try {
            this.heureDebut = LocalTime.parse(heureDebut);
            this.heureFin = LocalTime.parse(heureFin);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Format d'heure invalide. Utilisez HH:MM (ex: 08:30)", e);
        }
        
        // Vérifier que l'heure de fin est après l'heure de début
        if (!this.heureFin.isAfter(this.heureDebut)) {
            throw new IllegalArgumentException("L'heure de fin doit être après l'heure de début");
        }
    }

    // Constructeur avec objets LocalTime
    public Planning(JourSemaine jour, LocalTime heureDebut, LocalTime heureFin, String description) {
        if (jour == null) {
            throw new IllegalArgumentException("Le jour ne peut pas être null");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La description ne peut pas être vide");
        }
        if (!heureFin.isAfter(heureDebut)) {
            throw new IllegalArgumentException("L'heure de fin doit être après l'heure de début");
        }

        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.description = description.trim();
    }

    // Getters
    public JourSemaine getJour() {
        return jour;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public String getDescription() {
        return description;
    }

    // Méthode utilitaire pour vérifier le chevauchement
    public boolean chevaucheAvec(Planning autre) {
        return ConflictChecker.checkConflict(this, autre);
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %s à %s", 
            description, jour, heureDebut, heureFin);
    }
}
