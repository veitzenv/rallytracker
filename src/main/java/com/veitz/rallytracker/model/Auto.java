package com.veitz.rallytracker.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import jakarta.persistence.*;

@Getter @Setter
@Entity // Le digo a Spring que esta clase es una tabla en la BD.
public class Auto {
    @Id // Indico el atributo que es PK.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indico que sea autoincremental.
    private int id;
    private String piloto;
    private int dorsal;
    private int tiempoTotal;
    @OneToMany (mappedBy = "referencia") // Relación uno a muchos. El mappedBy indica que esta clase es la "dueña" de la relación.
    private List<Resultado> listResultados;

}
