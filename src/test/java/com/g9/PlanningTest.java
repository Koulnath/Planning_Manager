package com.g9;

import com.g9.model.Planning;
import com.g9.model.Planning.Slot;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlanningTest {

    private Planning planning;

    @Before
    public void setUp() {
        planning = new Planning();
    }

    @Test
    public void addValidSlot() {
        planning.addSlot("Lundi", "08:00", "10:00", "Maths");
        List<Slot> slots = planning.getSlots();
        assertEquals(1, slots.size());
        assertEquals("Maths", slots.get(0).subject);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullDayThrows() {
        planning.addSlot(null, "08:00", "10:00", "X");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addEmptySubjectThrows() {
        planning.addSlot("Mardi", "08:00", "09:00", "   ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTimeFormatThrows() {
        planning.addSlot("Mercredi", "8h00", "10h00", "Info");
    }

    @Test(expected = IllegalArgumentException.class)
    public void endBeforeStartThrows() {
        planning.addSlot("Jeudi", "10:00", "09:00", "TP");
    }

    @Test(expected = IllegalArgumentException.class)
    public void overlappingSlotsOnSameDayThrows() {
        planning.addSlot("Vendredi", "08:00", "10:00", "Algo");
        planning.addSlot("Vendredi", "09:30", "11:00", "Reseau");
    }

    @Test
    public void adjacentSlotsAreAllowed() {
        planning.addSlot("Lundi", "08:00", "10:00", "S1");
        planning.addSlot("Lundi", "10:00", "12:00", "S2");
        assertEquals(2, planning.getSlots().size());
    }
}
