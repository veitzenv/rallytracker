package com.veitz.rallytracker.service;

import com.veitz.rallytracker.model.Auto;
import com.veitz.rallytracker.model.Resultado;
import com.veitz.rallytracker.repository.AutoRepository;
import com.veitz.rallytracker.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RallyService {
    private final AutoRepository autoRepository; // Le paso la referencia al repository para usarlo.
    private final ResultadoRepository resultadoRepository; // Lo mismo que la linea anterior.

    public RallyService (AutoRepository autoRepository, ResultadoRepository resultadoRepository) {
        this.autoRepository = autoRepository;
        this.resultadoRepository = resultadoRepository;
    } // Constructor con los repositorios como parámetros.

    public void procesarDatos(int id, int tramo, int tiempo, int penalizaciones) {
        Auto auto = autoRepository.findById(id).orElseThrow(() -> new RuntimeException("Auto no encontrado.")); // Método para procesar los datos que me llegan de X auto.

        // Creo un nuevo objeto Resultado y le cargo los datos que me llegaron.
        Resultado r = new Resultado();
        r.setReferencia(auto);
        r.setTramo(tramo);
        r.setTiempo(tiempo);
        r.setPenalizaciones(penalizaciones);

        resultadoRepository.save(r); // Guardo mi objeto resultado con los datos que llegaron en la BD.

        // Actualizo el atributo "tiempoTotal" mi auto (el que cree en la línea 21) sumándole el tiempo del tramo actual + penalizaciones.
        auto.setTiempoTotal(auto.getTiempoTotal() + tiempo + penalizaciones);

        autoRepository.save(auto); // Guardo los datos actualizados en la BD.
    }

    public Auto liderGeneral() {
        List<Auto> todos = autoRepository.findAll(); // Creo la lista de tipo Auto con el nombre "todos".
        Auto lider = todos.get(0); // Creo una variable de tipo Auto que llama líder y le asigno como valor, el primer elemento de la lista.

        // Recorro la lista "todos" y comparo si el tiempo total del auto en el que estoy parado (a) es menor al tiempo total del lider
        // Si lo es, asigno al auto actual (a) como el lider.
        for (Auto a : todos) {
            if (a.getTiempoTotal() < lider.getTiempoTotal()) {
                lider = a;
            }

        }
        return lider;
    }

    public Resultado liderDeTramo (int tramo) {
        List<Resultado> todos = resultadoRepository.findAll();
        Resultado liderTramo = null;

        // Recorro la lista de Resultado (que es el objeto que guarda tramos).
        // Verifico que sea del tramo buscado y que sea el primero o el de mejor tiempo visto.
        for (Resultado a : todos) {
            if (a.getTramo() == tramo && liderTramo == null || a.getTramo() == tramo && liderTramo != null && a.getTiempo() < liderTramo.getTiempo()) {
                liderTramo = a;
            }
        }
        return liderTramo;
    }

    public List<Auto> ordenarTabla() {
        List<Auto> posiciones = autoRepository.findAll(); // Traigo todos los autos
        posiciones.sort((a1, a2) -> a1.getTiempoTotal() - a2.getTiempoTotal()); // Comparo los tiempos totales de cada auto que traje para ordenarlos de menor a mayor.

        return posiciones;
    }

    public Auto inscribirAuto(String piloto, int dorsal) {
        Auto auto = new Auto();
        auto.setPiloto(piloto);
        auto.setDorsal(dorsal);
        autoRepository.save(auto);

        return auto;

    }


}
