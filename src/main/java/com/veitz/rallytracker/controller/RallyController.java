package com.veitz.rallytracker.controller;

import com.veitz.rallytracker.model.Auto;
import com.veitz.rallytracker.model.Resultado;
import com.veitz.rallytracker.service.RallyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class RallyController {

    private final RallyService rallyService;

    public RallyController(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    @GetMapping("/liderGeneral")
    public Auto liderGeneral() {
        return rallyService.liderGeneral();
    }

    @GetMapping("/liderDeTramo/{tramo}")
    public Resultado liderDeTramo(@PathVariable int tramo) {
        return rallyService.liderDeTramo(tramo);
    }

    @GetMapping("/ordenarTabla")
    public List<Auto> ordenarTabla() {
        return rallyService.ordenarTabla();
    }

    @PostMapping("/procesar")
    public void procesarDatos(@RequestParam int id, @RequestParam int tramo, @RequestParam int tiempo, @RequestParam int penalizaciones) {
        rallyService.procesarDatos(id, tramo, tiempo, penalizaciones);
    }

    @PostMapping("/inscribir")
    public Auto inscribirAuto(@RequestParam String piloto, @RequestParam int dorsal) {
        return rallyService.inscribirAuto(piloto, dorsal);
    }

    }

