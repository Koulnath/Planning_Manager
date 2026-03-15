package com.g9.service;

import com.g9.model.JourSemaine;
import com.g9.model.Planning;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CourseServiceTest {
    private CourseService service;

    @Before
    public void setUp() {
        service = new CourseService();
    }

    @Test
    public void testAddCourseSuccess() {
        Planning p = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        service.addCourse(p);
        assertEquals(1, service.getAllCourses().size());
        assertEquals(1, service.getAllCourses().get(0).getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCourseConflict() {
        service.addCourse(new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00"));
        // Tentative d'ajouter un cours en conflit (même prof même heure)
        service.addCourse(new Planning("SQL", "Juvenal", "Salle 2", JourSemaine.LUNDI, "09:00", "11:00"));
    }

    @Test
    public void testDeleteCourse() {
        Planning p = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        service.addCourse(p);
        int id = p.getId();
        assertTrue(service.deleteCourse(id));
        assertTrue(service.getAllCourses().isEmpty());
    }

    @Test
    public void testUpdateCourse() {
        Planning p1 = new Planning("Java", "Juvenal", "Salle 1", JourSemaine.LUNDI, "08:00", "10:00");
        service.addCourse(p1);

        Planning updated = new Planning("Java Avancé", "Juvenal", "Salle 2", JourSemaine.LUNDI, "14:00", "16:00");
        service.updateCourse(p1.getId(), updated);

        Planning result = service.getCourseById(p1.getId()).get();
        assertEquals("Java Avancé", result.getNomCours());
        assertEquals("Salle 2", result.getSalle());
    }
}
