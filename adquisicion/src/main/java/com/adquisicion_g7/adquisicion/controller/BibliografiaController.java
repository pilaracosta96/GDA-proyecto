package com.adquisicion_g7.adquisicion.controller;

import com.adquisicion_g7.adquisicion.dto.BibliografiaDTO;
import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.service.BibliografiaService;
import com.adquisicion_g7.adquisicion.service.EditorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bibliografias")
public class BibliografiaController {

    private final BibliografiaService bibliografiaService;
   // private final EditorialService editorialService;
    @Autowired
    public BibliografiaController(BibliografiaService bibliografiaService) {
        this.bibliografiaService = bibliografiaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Bibliografia> guardarBibliografia(@RequestBody @Valid BibliografiaDTO bibliografiaDTO) {

        //
        return ResponseEntity.ok(bibliografiaService.guardarBibliografia(bibliografiaDTO));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> removerBibliografia(@PathVariable Long id) {
        bibliografiaService.removerBibliografia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Bibliografia>> buscarPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(bibliografiaService.buscarPorTitulo(titulo));
    }

    @GetMapping("/apellidoAutor/{apellidoAutor}")
    public ResponseEntity<List<Bibliografia>> buscarPorApellidoAutor(@PathVariable String apellidoAutor) {
        return ResponseEntity.ok(bibliografiaService.buscarPorApellidoAutor(apellidoAutor));

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Bibliografia>> listarTodas() {
        return ResponseEntity.ok(bibliografiaService.listarTodas());
    }
    @GetMapping("/editorial/{nombreEditorial}")
    public ResponseEntity<List<Bibliografia>> buscarNombreEditorial(@PathVariable String nombreEditorial) {
        List<Bibliografia> bibliografias = bibliografiaService.buscarNombreEditorial(nombreEditorial);
        return ResponseEntity.ok(bibliografias);
    }

    }
