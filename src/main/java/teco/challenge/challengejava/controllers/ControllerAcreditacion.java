package teco.challenge.challengejava.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teco.challenge.challengejava.dominio.Acreditacion;
import teco.challenge.challengejava.dto.DTOAcreditacion;
import teco.challenge.challengejava.servicios.ServicioAcreditacion;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/acreditaciones")
public class ControllerAcreditacion {

    @Autowired
    private ServicioAcreditacion servicioAcreditacion;

    @PostMapping
    public ResponseEntity<Acreditacion> saveAcreditacion(@RequestBody @Valid DTOAcreditacion dtoAcreditacion) {

        if (dtoAcreditacion.getImporte().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            return ResponseEntity.ok(servicioAcreditacion.saveAcreditacion(dtoAcreditacion));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<Acreditacion>> getAcreditaciones() {

        return ResponseEntity.ok(servicioAcreditacion.getAcreditaciones());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Acreditacion> getAcreditacion(@PathVariable Long id) {

        return ResponseEntity.ok(servicioAcreditacion.getAcreditacion(id));

    }

    public ControllerAcreditacion(ServicioAcreditacion servicioAcreditacion) {

        this.servicioAcreditacion = servicioAcreditacion;

    }

}
