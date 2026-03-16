package com.g9.service;

import com.g9.model.JourSemaine;
import com.g9.model.Planning;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConflictCheckerTest {

    private List<Planning> coursExistants;

    @Before
    public void setUp() {
        coursExistants = new ArrayList<>();
        // On crée un cours de référence pour nos tests
        // Juvenal enseigne en Salle 1, le Lundi de 08:00 à 10:00
        coursExistants.add(new Planning("Java Core", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00"));
    }

    @Test
    public void testAucunConflitHeureDifferente() {
        // Un cours le même jour, mais qui commence pile à la fin du premier (10:00)
        Planning nouveauCours = new Planning("SQL", "Sokeng", "Salle 2", JourSemaine.LUNDI, "10:00", "12:00");
        
        // La méthode valider ne doit lever aucune exception
        ConflictChecker.valider(nouveauCours, coursExistants);
    }

    @Test
    public void testAucunConflitJourDifferent() {
        // Le même prof et la même salle, mais un jour différent (Mardi)
        Planning nouveauCours = new Planning("Java Avancé", "Juvenal", "Salle 1", JourSemaine.MARDI, "08:00", "10:00");
        
        // La méthode valider ne doit lever aucune exception
        ConflictChecker.valider(nouveauCours, coursExistants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConflitMemeProfesseur() {
        // Juvenal tente de donner un autre cours le Lundi à 09:00 (chevauchement avec 08:00-10:00)
        Planning nouveauCours = new Planning("Spring Boot", "Juvenal", "Salle 2", JourSemaine.LUNDI, "09:00", "11:00");
        
        // Doit déclencher une IllegalArgumentException
        ConflictChecker.valider(nouveauCours, coursExistants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConflitMemeSalle() {
        // Un autre prof tente d'utiliser la Salle 1 le Lundi à 08:30 (chevauchement avec 08:00-10:00)
        Planning nouveauCours = new Planning("Maths", "Nathanael", "Salle 1", JourSemaine.LUNDI, "08:30", "10:30");
        
        // Doit déclencher une IllegalArgumentException
        ConflictChecker.valider(nouveauCours, coursExistants);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConflitInclusionTotale() {
        // Un cours qui se déroule entièrement pendant le cours existant (08:30 - 09:30)
        Planning nouveauCours = new Planning("Mini-Séminaire", "Favor", "Salle 1", JourSemaine.LUNDI, "08:30", "09:30");
        
        // Doit déclencher une IllegalArgumentException
        ConflictChecker.valider(nouveauCours, coursExistants);
    }
}