package com.g9.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Planning {

	public static class Slot {
		public final String day;
		public final LocalTime start;
		public final LocalTime end;
		public final String subject;

		public Slot(String day, LocalTime start, LocalTime end, String subject) {
			this.day = day;
			this.start = start;
			this.end = end;
			this.subject = subject;
		}
	}

	private final List<Slot> slots = new ArrayList<>();

	public void addSlot(String day, String startTime, String endTime, String subject) {
		if (day == null || startTime == null || endTime == null || subject == null) {
			throw new IllegalArgumentException("Aucun argument ne peut être null");
		}
		day = day.trim();
		subject = subject.trim();
		if (day.isEmpty() || subject.isEmpty()) {
			throw new IllegalArgumentException("Day et subject ne peuvent pas être vides");
		}

		LocalTime start;
		LocalTime end;
		try {
			start = parseTime(startTime);
			end = parseTime(endTime);
		} catch (Exception e) {
			throw new IllegalArgumentException("Format d'heure invalide: attendu HH:mm", e);
		}
		if (!end.isAfter(start)) {
			throw new IllegalArgumentException("L'heure de fin doit être après l'heure de début");
		}

		// Vérifier chevauchement
		for (Slot s : slots) {
			if (s.day.equalsIgnoreCase(day) && overlaps(s.start, s.end, start, end)) {
				throw new IllegalArgumentException("Chevauchement détecté avec un autre créneau");
			}
		}

		slots.add(new Slot(day, start, end, subject));
	}

	public List<Slot> getSlots() {
		return Collections.unmodifiableList(slots);
	}

	public void clear() {
		slots.clear();
	}

	private static LocalTime parseTime(String value) {
		// Accept formats like "08:00" or "8:00"
		return LocalTime.parse(value);
	}

	private static boolean overlaps(LocalTime aStart, LocalTime aEnd, LocalTime bStart, LocalTime bEnd) {
		return !aEnd.isBefore(bStart) && !bEnd.isBefore(aStart) && (aStart.isBefore(bEnd) && bStart.isBefore(aEnd));
	}
}
