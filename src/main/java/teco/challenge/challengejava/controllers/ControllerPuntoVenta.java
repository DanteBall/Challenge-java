package teco.challenge.challengejava.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.PuntoDeVenta;
import teco.challenge.challengejava.dto.DTOPuntoVenta;
import teco.challenge.challengejava.servicios.ServicioCamino;
import teco.challenge.challengejava.servicios.ServicioPuntoVenta;

import java.util.List;

@RestController
@RequestMapping("/puntos")
public class ControllerPuntoVenta {

    @Autowired
    private ServicioPuntoVenta servicioPuntoVenta;

    @Autowired
    private ServicioCamino servicioCamino;

    @GetMapping
    public ResponseEntity<List<PuntoDeVenta>> getPuntosVenta() {
        return ResponseEntity.ok(servicioPuntoVenta.getPuntosVenta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuntoDeVenta> getPuntoVenta(@PathVariable Long id) {

        return ResponseEntity.ok(servicioPuntoVenta.getPuntoVenta(id));

    }

    @GetMapping("/{id}/caminos")
    public ResponseEntity<List<Camino>> getCaminos(@PathVariable Long id) {
        return ResponseEntity.ok(servicioCamino.getCaminosDirectos(id));
    }

    @PostMapping
    public ResponseEntity<PuntoDeVenta> savePuntoVenta(@RequestBody @Valid DTOPuntoVenta dtoPuntoVenta) {
        PuntoDeVenta puntoVenta = new PuntoDeVenta();
        puntoVenta.setNombre(dtoPuntoVenta.getNombre());
        return ResponseEntity.ok(servicioPuntoVenta.savePuntoVenta(puntoVenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuntoDeVenta> actualizarPuntoVenta(@PathVariable Long id,@RequestBody @Valid DTOPuntoVenta dtoPuntoVenta) {
        PuntoDeVenta puntoVenta = new PuntoDeVenta();
        puntoVenta.setNombre(dtoPuntoVenta.getNombre());
        puntoVenta.setId(id);
        return ResponseEntity.ok(servicioPuntoVenta.savePuntoVenta(puntoVenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePuntoVenta(@PathVariable Long id) {
        if( servicioPuntoVenta.getPuntoVenta(id) == null){
            return ResponseEntity.badRequest().body("error: el punto de venta no existe");
        }
        servicioPuntoVenta.deletePuntoVenta(id);

        return ResponseEntity.ok("punto borrado correctamente");
    }


    public ControllerPuntoVenta(ServicioPuntoVenta servicioPuntoVenta) {
        this.servicioPuntoVenta = servicioPuntoVenta;
    }
}
