package com.g9.dao;

import com.g9.model.Planning;
import java.util.List;
import java.util.Optional;

/**
 * Interface définissant les opérations de persistance pour les cours.
 */
public interface CourseDAO {
    void save(Planning planning);

    void update(Planning planning);

    void delete(int id);

    List<Planning> findAll();

    Optional<Planning> findById(int id);
}
