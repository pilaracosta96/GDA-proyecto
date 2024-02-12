package com.adquisicion_g7.adquisicion.controller;

import com.adquisicion_g7.adquisicion.dto.BibliografiaDTO;
import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
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
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/bibliografias")
public class BibliografiaController {

    private final BibliografiaService bibliografiaService;
   // private final EditorialService editorialService;
    @Autowired
    public BibliografiaController(BibliografiaService bibliografiaService) {
        this.bibliografiaService = bibliografiaService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<MensajeDTO> guardarBibliografia(@RequestBody @Valid BibliografiaDTO bibliografiaDTO) {
        MensajeDTO mensajeDTO = bibliografiaService.guardarBibliografia(bibliografiaDTO);
        return ResponseEntity.ok(mensajeDTO);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> removerBibliografia(@PathVariable Long id) {
        bibliografiaService.removerBibliografia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<MensajeDTO> buscarPorTitulo(@PathVariable String titulo) {
        MensajeDTO mensajeDTO = bibliografiaService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(mensajeDTO);
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
    public ResponseEntity<MensajeDTO> buscarNombreEditorial(@PathVariable String nombreEditorial) {
        MensajeDTO mensajeDTO = bibliografiaService.buscarNombreEditorial(nombreEditorial);
        return ResponseEntity.ok(mensajeDTO);
    }
    @DeleteMapping("/eliminarPorISBN/{isbn}")
    public ResponseEntity<MensajeDTO> eliminarBibliografiaPorISBN(@PathVariable Long isbn){
        MensajeDTO mensajeDTO = bibliografiaService.eliminarBibliografiaPorISBN(isbn);
        return ResponseEntity.ok(mensajeDTO);
    }

    @PutMapping("/recuperarPorISBN/{isbn}")
    public ResponseEntity<MensajeDTO> recuperarBibliografiaPorISBN(@PathVariable Long isbn) {
        MensajeDTO mensajeDTO = bibliografiaService.recuperarBibliografiaPorISBN(isbn);
        return ResponseEntity.ok(mensajeDTO);
    }

}
