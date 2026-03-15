package com.g9.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Modèle représentant un cours dans l'emploi du temps.
 * Version unifiée incluant le support des jours et des heures précises
 * (LocalTime).
 */
public class Planning {
    private int id;
    private String nomCours;
    private String enseignant;
    private String salle;
    private JourSemaine jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    // Constructeur par défaut
    public Planning() {
    }

    // Constructeur complet
    public Planning(int id, String nomCours, String enseignant, String salle, JourSemaine jour, String heureDebut,
            String heureFin) {
        this.id = id;
        this.nomCours = nomCours;
        this.enseignant = enseignant;
        this.salle = salle;
        this.jour = jour;
        setHeureDebut(heureDebut);
        setHeureFin(heureFin);
        validateTimes();
    }

    // Version simplifiée pour les tests
    public Planning(String nomCours, String enseignant, String salle, JourSemaine jour, String heureDebut,
            String heureFin) {
        this(0, nomCours, enseignant, salle, jour, heureDebut, heureFin);
    }

    private void validateTimes() {
        if (heureDebut != null && heureFin != null && !heureFin.isAfter(heureDebut)) {
            throw new IllegalArgumentException("L'heure de fin doit être après l'heure de début (" + nomCours + ")");
        }
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public JourSemaine getJour() {
        return jour;
    }

    public void setJour(JourSemaine jour) {
        this.jour = jour;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureStr) {
        try {
            this.heureDebut = LocalTime.parse(heureStr, FORMATTER);
        } catch (DateTimeParseException e) {
            // Fallback pour format H:mm si nécessaire ou lever une exception claire
            this.heureDebut = LocalTime.parse(heureStr);
        }
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureStr) {
        try {
            this.heureFin = LocalTime.parse(heureStr, FORMATTER);
        } catch (DateTimeParseException e) {
            this.heureFin = LocalTime.parse(heureStr);
        }
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (%s) | %s %s - %s",
                id, nomCours, enseignant, salle, jour, heureDebut, heureFin);
    }
}
