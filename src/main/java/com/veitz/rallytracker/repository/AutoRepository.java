package com.veitz.rallytracker.repository;
import com.veitz.rallytracker.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoRepository extends JpaRepository<Auto, Integer> {
}
