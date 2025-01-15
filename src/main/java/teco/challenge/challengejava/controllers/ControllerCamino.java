package teco.challenge.challengejava.controllers;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teco.challenge.challengejava.dominio.Camino;
import teco.challenge.challengejava.dominio.ResultadoCosto;
import teco.challenge.challengejava.dto.DTOCamino;
import teco.challenge.challengejava.dto.Puntos;
import teco.challenge.challengejava.servicios.ServicioCamino;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/caminos")
public class ControllerCamino {

    private ServicioCamino servicioCamino;

    @GetMapping
    public ResponseEntity<List<Camino>> getCaminos() {
        return ResponseEntity.ok(servicioCamino.getCaminos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camino> getCamino(Long id) {

        return ResponseEntity.ok(servicioCamino.getCamino(id));

    }

    @PostMapping
    public ResponseEntity<String> saveCamino(@RequestBody @Valid DTOCamino camino) {
        if(camino.getCosto().compareTo(BigDecimal.ZERO) <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error: el costo debe ser mayor a 0");
        }

        try {
            servicioCamino.saveCamino(camino);

        } catch (DataIntegrityViolationException v) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error: ya existe un camino entre los puntos");
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error: el camino posee un punto de venta inexistente");
        }

        return ResponseEntity.ok("camino guardado correctamente");
    }

    @GetMapping("/calcularCosto")
    public ResponseEntity<ResultadoCosto> calcularCostoMinimo(@RequestBody @Valid Puntos puntos) {

        return ResponseEntity.ok(servicioCamino.calcularCostoMinimo(puntos.getPuntoA(), puntos.getPuntoB()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCamino(@PathVariable Long id) {
        servicioCamino.deleteCamino(id);
        return ResponseEntity.ok("camino borrado correctamente");
    }

    public ControllerCamino(ServicioCamino servicioCamino) {

        this.servicioCamino = servicioCamino;

    }
}
