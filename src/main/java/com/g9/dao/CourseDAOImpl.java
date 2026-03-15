package com.g9.dao;

import com.g9.model.Planning;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation de CourseDAO.
 * Actuellement utilise une liste en mémoire (Mock).
 * Sokeng devra remplacer le code par des requêtes JDBC.
 */
public class CourseDAOImpl implements CourseDAO {
    private final List<Planning> databaseMock = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void save(Planning p) {
        p.setId(nextId++);
        databaseMock.add(p);
        System.out.println("💾 DB MOCK: Cours sauvegardé -> " + p.getNomCours());
    }

    @Override
    public void update(Planning p) {
        for (int i = 0; i < databaseMock.size(); i++) {
            if (databaseMock.get(i).getId() == p.getId()) {
                databaseMock.set(i, p);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        databaseMock.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Planning> findAll() {
        return new ArrayList<>(databaseMock);
    }

    @Override
    public Optional<Planning> findById(int id) {
        return databaseMock.stream().filter(p -> p.getId() == id).findFirst();
    }
}
