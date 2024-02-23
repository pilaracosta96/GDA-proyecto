
package com.adquisicion_g7.adquisicion.controller;

import com.adquisicion_g7.adquisicion.dto.EquipoInfraestructuraDTO;
import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.entities.EquipoInfraestructura;
import com.adquisicion_g7.adquisicion.service.EquipoInfraestructuraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/equipoInfraestructura")
public class EquipoInfraestructuraController {

    private final EquipoInfraestructuraService equipoInfraestructuraService;

    @Autowired
    public EquipoInfraestructuraController(EquipoInfraestructuraService equipoInfraestructuraService) {
        this.equipoInfraestructuraService = equipoInfraestructuraService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<MensajeDTO> guardarEquipoInfraestructura(@RequestBody @Valid EquipoInfraestructuraDTO equipoInfraestructuraDTO) {
        MensajeDTO mensajeDTO = equipoInfraestructuraService.guardarEquipoInfraestructura(equipoInfraestructuraDTO);
        return ResponseEntity.ok(mensajeDTO);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> removerEquipoInfraestructura(@PathVariable Long id) {
        equipoInfraestructuraService.removerEquipoInfraestructura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombreTipoEquipo}")
    public ResponseEntity<MensajeDTO> buscarPorNombreTipoEquipo(@PathVariable String nombreTipoEquipo) {
        MensajeDTO mensajeDTO = equipoInfraestructuraService.buscarNombreTipoEquipo(nombreTipoEquipo);
        return ResponseEntity.ok(mensajeDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EquipoInfraestructura>> listarTodas() {
        return ResponseEntity.ok(equipoInfraestructuraService.listarTodas());

    }

    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<MensajeDTO> eliminarEquipoPorId(@PathVariable Long id) {
        MensajeDTO mensajeDTO = equipoInfraestructuraService.eliminarEquipoPorId(id);
        return ResponseEntity.ok(mensajeDTO);
    }

    @PutMapping("/recuperarPorId/{id}")
    public ResponseEntity<MensajeDTO> recuperarEquipoPorId(@PathVariable Long id) {
        MensajeDTO mensajeDTO = equipoInfraestructuraService.recuperarEquipoPorId(id);
        return ResponseEntity.ok(mensajeDTO);

    }

    @DeleteMapping("/eliminarPorNumeroSerie/{numeroSerie}")
    public ResponseEntity<MensajeDTO> eliminarEquipoPorNumeroSerie(@PathVariable String numeroSerie) {
        MensajeDTO mensajeDTO = equipoInfraestructuraService.eliminarEquipoPorNumeroSerie(numeroSerie);
        return ResponseEntity.ok(mensajeDTO);
    }

    @PutMapping("/recuperarPorNumeroSerie/{numeroSerie}")
    public ResponseEntity<MensajeDTO> recuperarEquipoPorNumeroSerie(@PathVariable String numeroSerie) {
        MensajeDTO mensajeDTO = equipoInfraestructuraService.recuperarEquipoPorNumeroSerie(numeroSerie);
        return ResponseEntity.ok(mensajeDTO);
    }
    @GetMapping("/buscarPorNumeroSerie/{numeroSerie}")
    public ResponseEntity<?> buscarPorNumeroSerie(@PathVariable String numeroSerie) {
        Optional<EquipoInfraestructura> equipoInfraestructuraOptional = equipoInfraestructuraService.buscarPorNumeroSerie(numeroSerie);

        if (equipoInfraestructuraOptional.isPresent()) {
            return ResponseEntity.ok(equipoInfraestructuraOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El número de serie no existe para equipos de infraestructura. Por favor, ingrese un número de serie válido.");
        }
    }
}

