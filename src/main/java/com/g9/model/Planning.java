package com.g9.model;

public class Planning {
    private int id;
    private String nomCours;
    private String enseignant;
    private String salle;
    private int heureDebut; // Format HHMM (ex: 0800 pour 08h00)
    private int heureFin;

    public Planning() {
    }

    public Planning(String nomCours, String enseignant, String salle, int heureDebut, int heureFin) {
        this.nomCours = nomCours;
        this.enseignant = enseignant;
        this.salle = salle;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

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

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "nomCours='" + nomCours + '\'' +
                ", enseignant='" + enseignant + '\'' +
                ", salle='" + salle + '\'' +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                '}';
    }
}
