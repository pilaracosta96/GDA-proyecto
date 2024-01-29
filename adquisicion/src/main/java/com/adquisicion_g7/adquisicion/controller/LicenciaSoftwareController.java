package com.adquisicion_g7.adquisicion.controller;

import com.adquisicion_g7.adquisicion.dto.BibliografiaDTO;
import com.adquisicion_g7.adquisicion.dto.LicenciaSoftwareDTO;
import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.LicenciaSoftware;
import com.adquisicion_g7.adquisicion.service.LicenciaSoftwareService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licenciasSoftware")
public class LicenciaSoftwareController {

    private final LicenciaSoftwareService licenciaSoftwareService;

    @Autowired
    public LicenciaSoftwareController(LicenciaSoftwareService licenciaSoftwareService) {
        this.licenciaSoftwareService = licenciaSoftwareService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<MensajeDTO> guardarLicenciaSoftware(@RequestBody @Valid LicenciaSoftwareDTO licenciaSoftwareDTO) {
        MensajeDTO mensajeDTO = licenciaSoftwareService.guardarLicenciaSoftware(licenciaSoftwareDTO);
        return ResponseEntity.ok(mensajeDTO);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> removerLicenciaSoftware(@PathVariable Long id) {
        licenciaSoftwareService.removerLicenciaSoftware(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MensajeDTO> buscarPorNombre(@PathVariable String nombre) {
        MensajeDTO mensajeDTO = licenciaSoftwareService.buscarPorNombre(nombre);
        return ResponseEntity.ok(mensajeDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LicenciaSoftware>> listarTodas() {
        return ResponseEntity.ok(licenciaSoftwareService.listarTodas());
    }

    @GetMapping("/fabricante/{nombreFabricante}")
    public ResponseEntity<MensajeDTO> buscarNombreFabricante(@PathVariable String nombreFabricante) {
        MensajeDTO mensajeDTO = licenciaSoftwareService.buscarNombreFabricante(nombreFabricante);
        return ResponseEntity.ok(mensajeDTO);
    }

    @DeleteMapping("/eliminarPorId/{id}")
    public ResponseEntity<MensajeDTO> eliminarLicenciaSoftwarePorId(@PathVariable Long id){
        MensajeDTO mensajeDTO = licenciaSoftwareService.eliminarLicenciaSoftwarePorId(id);
        return ResponseEntity.ok(mensajeDTO);
    }

    @PutMapping("/recuperarPorId/{id}")
    public ResponseEntity<MensajeDTO> recuperarLicenciaSoftwarePorId(@PathVariable Long id) {
        MensajeDTO mensajeDTO = licenciaSoftwareService.recuperarLicenciaSoftwarePorId(id);
        return ResponseEntity.ok(mensajeDTO);
    }
}
