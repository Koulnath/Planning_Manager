package com.g9.service;

import static org.junit.Assert.*;
import org.junit.Test;
import com.g9.model.Planning;

public class ConflictCheckerTest {

    @Test
    public void testConflictInSameSalle() {
        Planning p1 = new Planning("Java", "Nathanael", "Salle 1", 800, 1000);
        Planning p2 = new Planning("SQL", "Sokeng", "Salle 1", 900, 1100);

        assertTrue("Il devrait y avoir un conflit (chevauchement)", ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testNoConflictDifferentSalle() {
        Planning p1 = new Planning("Java", "Nathanael", "Salle 1", 800, 1000);
        Planning p2 = new Planning("SQL", "Sokeng", "Salle 2", 900, 1100);

        assertFalse("Pas de conflit car les salles sont différentes", ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testNoConflictSequential() {
        Planning p1 = new Planning("Java", "Nathanael", "Salle 1", 800, 1000);
        Planning p2 = new Planning("SQL", "Sokeng", "Salle 1", 1000, 1200);

        assertFalse("Pas de conflit car ils sont séquentiels (10h00 = pas de chevauchement)",
                ConflictChecker.hasConflict(p1, p2));
    }

    @Test
    public void testConflictFullOverlap() {
        Planning p1 = new Planning("Java", "Nathanael", "Salle 1", 800, 1200);
        Planning p2 = new Planning("SQL", "Sokeng", "Salle 1", 900, 1100);

        assertTrue("Conflit car p2 est inclus dans p1", ConflictChecker.hasConflict(p1, p2));
    }
}
