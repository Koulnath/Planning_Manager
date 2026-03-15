package com.g9.service;

import com.g9.model.JourSemaine;
import com.g9.model.Planning;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConflictCheckerTest {

    @Test
    public void testConflictMemeSalleMemeHeure() {
        Planning p1 = new Planning("Java", "Nathanael", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        Planning p2 = new Planning("SQL", "Sokeng", "Salle 1", JourSemaine.LUNDI, "09:00", "11:00");
        assertTrue("Il devrait y avoir un conflit de salle (chevauchement)", ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testConflictMemeProfMemeHeure() {
        Planning p1 = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        Planning p2 = new Planning("PHP", "Juvenal", "Salle 2", JourSemaine.LUNDI, "09:00", "11:00");
        assertTrue("Il devrait y avoir un conflit d'enseignant (même prof même heure)",
                ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testPasDeConflitJoursDifferents() {
        Planning p1 = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        Planning p2 = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.MARDI, "08:00", "10:00");
        assertFalse("Pas de conflit car jours différents", ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testPasDeConflitHeuresQuiSeTouchent() {
        Planning p1 = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        Planning p2 = new Planning("PHP", "Sokeng", "Salle 1", JourSemaine.LUNDI, "10:00", "12:00");
        assertFalse("Pas de conflit car les heures se touchent exactement", ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testConflitChevauchementTotal() {
        Planning p1 = new Planning("Java", "Favor", "Salle A", JourSemaine.MERCREDI, "08:00", "12:00");
        Planning p2 = new Planning("SQL", "Sokeng", "Salle A", JourSemaine.MERCREDI, "09:00", "11:00");
        assertTrue("Conflit car le cours p2 est inclus dans p1", ConflictChecker.hasConflict(p1, p2));
    }
}
