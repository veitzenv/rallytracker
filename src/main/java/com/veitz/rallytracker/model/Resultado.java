package com.veitz.rallytracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;


@Getter @Setter
@Entity
public class Resultado {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JsonIgnore
    private Auto referencia;
    private int tramo;
    private int tiempo;
    private int penalizaciones;
}
