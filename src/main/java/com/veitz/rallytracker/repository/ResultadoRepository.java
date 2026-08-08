package com.veitz.rallytracker.repository;

import com.veitz.rallytracker.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRepository extends JpaRepository<Resultado, Integer> {
}
