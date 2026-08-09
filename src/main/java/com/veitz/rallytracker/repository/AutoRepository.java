package com.veitz.rallytracker.repository;
import com.veitz.rallytracker.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

// El repository se encarga del CRUD a la BD.
public interface AutoRepository extends JpaRepository<Auto, Integer> {
}
