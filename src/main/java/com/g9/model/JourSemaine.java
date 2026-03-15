package com.g9.model;

public enum JourSemaine {
    LUNDI("Lundi"),
    MARDI("Mardi"),
    MERCREDI("Mercredi"),
    JEUDI("Jeudi"),
    VENDREDI("Vendredi"),
    SAMEDI("Samedi"),
    DIMANCHE("Dimanche");

    private final String nomFrancais;

    JourSemaine(String nomFrancais) {
        this.nomFrancais = nomFrancais;
    }

    public String getNomFrancais() {
        return nomFrancais;
    }

    @Override
    public String toString() {
        return nomFrancais;
    }
}
