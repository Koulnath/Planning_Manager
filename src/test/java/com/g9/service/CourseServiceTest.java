package com.g9.service;

import com.g9.model.JourSemaine;
import com.g9.model.Planning;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseServiceTest {
    private CourseService service;

    @Before
    public void setUp() {
        service = new CourseService();
    }

    @Test
    public void testAddCourseSuccess() {
        // On compte combien de cours il y a avant l'ajout
        int tailleAvant = service.getAllCourses().size();
        
        Planning p = new Planning("Java Test", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        service.addCourse(p);
        
        // On compte après l'ajout
        int tailleApres = service.getAllCourses().size();
        
        // La taille doit avoir augmenté de 1
        assertEquals(tailleAvant + 1, tailleApres);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCourseConflict() {
        // On ajoute un premier cours
        service.addCourse(new Planning("Java Conf", "Juvenal", "Salle Test", JourSemaine.LUNDI, "14:00", "16:00"));
        
        // Tentative d'ajouter un cours en conflit (même prof, même heure)
        // Cela doit déclencher une IllegalArgumentException
        service.addCourse(new Planning("SQL Conf", "Juvenal", "Salle 2", JourSemaine.LUNDI, "15:00", "17:00"));
    }

    @Test
    public void testDeleteCourse() {
        // On ajoute un cours spécifiquement pour le tester
        Planning p = new Planning("Test Delete", "Prof Test", "Salle 3", JourSemaine.MARDI, "08:00", "10:00");
        service.addCourse(p);
        
        // Puisque MySQL génère l'ID tout seul, on récupère le cours depuis la base pour avoir son vrai ID
        List<Planning> courses = service.getAllCourses();
        Planning savedCourse = null;
        
        // On cherche le cours qu'on vient d'ajouter
        for (Planning cours : courses) {
            if (cours.getNomCours().equals("Test Delete")) {
                savedCourse = cours;
                break;
            }
        }
        
        assertNotNull("Le cours aurait dû être sauvegardé", savedCourse);
        
        // On le supprime grâce à son véritable ID
        int id = savedCourse.getId();
        assertTrue(service.deleteCourse(id));
    }
}