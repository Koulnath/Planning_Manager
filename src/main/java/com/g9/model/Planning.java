package com.g9.model;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Planning {
    
    private int id; // Nommé "id" au lieu de "idPlanning" pour satisfaire CourseDAOImpl
    private String nomCours;
    private String nomProf;
    private String nomSalle;
    private JourSemaine jourSemaine;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    // Constructeur vide requis par JDBC/DAO
    public Planning() {
    }

    // Constructeur utilisé par Main.java (avec les heures en String)
    public Planning(String nomCours, String nomProf, String nomSalle, JourSemaine jourSemaine, String heureDebut, String heureFin) {
        this.nomCours = nomCours;
        this.nomProf = nomProf;
        this.nomSalle = nomSalle;
        this.jourSemaine = jourSemaine;
        
        try {
            this.heureDebut = LocalTime.parse(heureDebut);
            this.heureFin = LocalTime.parse(heureFin);
        } catch (DateTimeParseException e) {
            System.err.println("Erreur de format d'heure. Utilisez HH:mm");
        }
    }

    // Constructeur utilisé par la Base de données (avec les heures en LocalTime)
    public Planning(String nomCours, String nomProf, String nomSalle, JourSemaine jourSemaine, LocalTime heureDebut, LocalTime heureFin) {
        this.nomCours = nomCours;
        this.nomProf = nomProf;
        this.nomSalle = nomSalle;
        this.jourSemaine = jourSemaine;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    // --- GETTERS ET SETTERS ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomCours() { return nomCours; }
    public void setNomCours(String nomCours) { this.nomCours = nomCours; }

    public String getNomProf() { return nomProf; }
    public void setNomProf(String nomProf) { this.nomProf = nomProf; }

    public String getNomSalle() { return nomSalle; }
    public void setNomSalle(String nomSalle) { this.nomSalle = nomSalle; }

    public JourSemaine getJourSemaine() { return jourSemaine; }
    public void setJourSemaine(JourSemaine jourSemaine) { this.jourSemaine = jourSemaine; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    @Override
    public String toString() {
        return "ID " + id + " | " + jourSemaine + " [" + heureDebut + " - " + heureFin + "] : " 
                + nomCours + " (Prof: " + nomProf + ", Salle: " + nomSalle + ")";
    }
}