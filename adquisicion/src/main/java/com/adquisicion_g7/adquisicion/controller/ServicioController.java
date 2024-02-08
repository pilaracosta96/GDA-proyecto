package com.adquisicion_g7.adquisicion.controller;

import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.dto.ServicioDTO;
import com.adquisicion_g7.adquisicion.entities.Servicio;
import com.adquisicion_g7.adquisicion.service.ServicioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/servicio")
public class ServicioController {

    private final ServicioService servicioService;

    @Autowired
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<MensajeDTO> guardarServicio(@RequestBody @Valid ServicioDTO servicioDTO) {
        MensajeDTO mensajeDTO = servicioService.guardarServicio(servicioDTO);
        return ResponseEntity.ok(mensajeDTO);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> removerServicio(@PathVariable Long id) {
        servicioService.removerServicio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombreTipoServicio}")
    public  ResponseEntity<MensajeDTO> buscarPorNombreTipoServicio(@PathVariable String nombreTipoServicio) {
        MensajeDTO mensajeDTO = servicioService.buscarNombreTipoServicio(nombreTipoServicio);
        return ResponseEntity.ok(mensajeDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Servicio>> listarTodas() {
        return ResponseEntity.ok(servicioService.listarTodas());

    }
    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<MensajeDTO> eliminarServcioPorId(@PathVariable Long id) {
        MensajeDTO mensajeDTO = servicioService.eliminarServicioPorId(id);
        return ResponseEntity.ok(mensajeDTO);
    }
    @PutMapping("/recuperarPorId/{id}")
    public ResponseEntity<MensajeDTO> recuperarServicioPorId(@PathVariable Long id) {
        MensajeDTO mensajeDTO = servicioService.recuperarServicioPorId(id);
        return ResponseEntity.ok(mensajeDTO);

    }


}
