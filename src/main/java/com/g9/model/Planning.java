package com.g9.model;

import java.time.LocalTime;

public class Planning {
    
    // Attributs correspondants à la base de données
    private int idPlanning;
    private int idCours;
    private int idSalle;
    private int idEnseignant;
    private String jourSemaine;
    private LocalTime heureDebut; // Utilisation de LocalTime pour gérer "08:00" facilement
    private LocalTime heureFin;

    // Constructeur vide (souvent nécessaire pour JDBC)
    public Planning() {
    }

    // Constructeur complet (pour la lecture depuis la BD)
    public Planning(int idPlanning, int idCours, int idSalle, int idEnseignant, String jourSemaine, LocalTime heureDebut, LocalTime heureFin) {
        this.idPlanning = idPlanning;
        this.idCours = idCours;
        this.idSalle = idSalle;
        this.idEnseignant = idEnseignant;
        this.jourSemaine = jourSemaine;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    // Constructeur sans ID (pour l'insertion dans la BD depuis l'interface)
    public Planning(int idCours, int idSalle, int idEnseignant, String jourSemaine, LocalTime heureDebut, LocalTime heureFin) {
        this.idCours = idCours;
        this.idSalle = idSalle;
        this.idEnseignant = idEnseignant;
        this.jourSemaine = jourSemaine;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    // --- GETTERS ET SETTERS ---

    public int getIdPlanning() { return idPlanning; }
    public void setIdPlanning(int idPlanning) { this.idPlanning = idPlanning; }

    public int getIdCours() { return idCours; }
    public void setIdCours(int idCours) { this.idCours = idCours; }

    public int getIdSalle() { return idSalle; }
    public void setIdSalle(int idSalle) { this.idSalle = idSalle; }

    public int getIdEnseignant() { return idEnseignant; }
    public void setIdEnseignant(int idEnseignant) { this.idEnseignant = idEnseignant; }

    public String getJourSemaine() { return jourSemaine; }
    public void setJourSemaine(String jourSemaine) { this.jourSemaine = jourSemaine; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    // Pour faciliter l'affichage des tests dans la console
    @Override
    public String toString() {
        return "Planning [" + jourSemaine + " de " + heureDebut + " à " + heureFin + " - Salle ID: " + idSalle + "]";
    }
}
