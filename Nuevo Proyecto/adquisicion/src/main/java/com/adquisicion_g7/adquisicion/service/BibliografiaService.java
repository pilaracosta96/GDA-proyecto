package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.dto.BibliografiaDTO;
import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.entities.TipoMaterial;
import com.adquisicion_g7.adquisicion.repository.BibliografiaRepository;
import com.adquisicion_g7.adquisicion.repository.EditorialRepository;
import com.adquisicion_g7.adquisicion.repository.TipoMaterialRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BibliografiaService {

    private final BibliografiaRepository bibliografiaRepository;
    private final EditorialRepository editorialRepository;

    private final TipoMaterialRepository tipoMaterialRepository;


    @Autowired
    public BibliografiaService(BibliografiaRepository bibliografiaRepository, EditorialRepository editorialRepository, TipoMaterialRepository tipoMaterialRepository) {
        this.bibliografiaRepository = bibliografiaRepository;
        this.editorialRepository = editorialRepository;
        this.tipoMaterialRepository = tipoMaterialRepository;
    }

    @Transactional
    public MensajeDTO guardarBibliografia(BibliografiaDTO bibliografiaDTO) {
        String nombreEditorial = bibliografiaDTO.getEditorial().toUpperCase();
        String nombretipoMaterial = bibliografiaDTO.getTipoMaterial().toUpperCase();
        Optional<Editorial> editorialOptional = editorialRepository.findByNombreEditorial(nombreEditorial);
        TipoMaterial tipoMaterial = new TipoMaterial(null, nombretipoMaterial);
        tipoMaterialRepository.save(tipoMaterial);


        Bibliografia nuevaBibliografia = convertirDtoAEntidad(bibliografiaDTO);
        nuevaBibliografia.setTipoMaterial(tipoMaterial);
        nuevaBibliografia.setEliminada(false);

        if (editorialOptional.isEmpty()) {
            Editorial nuevaEditorial = new Editorial(nombreEditorial);
            editorialRepository.save(nuevaEditorial);
            nuevaBibliografia.setEditorial(nuevaEditorial);
            bibliografiaRepository.save(nuevaBibliografia);
            return new MensajeDTO("Bibliografía guardada correctamente.");
        }
        nuevaBibliografia.setEditorial(editorialOptional.get());
        bibliografiaRepository.save(nuevaBibliografia);
        return new MensajeDTO("Bibliografía guardada correctamente.");
    }


    @Transactional
    public MensajeDTO eliminarBibliografiaPorISBN(Long isbn) {
        Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbn(isbn);
        bibliografiaOptional.ifPresent(bibliografia -> {
            bibliografia.setEliminada(true);
            bibliografiaRepository.save(bibliografia);
        });
        String mensaje = bibliografiaOptional.isPresent() ? "Bibliografía eliminada correctamente." : "No se encontró bibliografía con el ISBN proporcionado.";
        return new MensajeDTO(mensaje);
    }

    @Transactional
    public MensajeDTO recuperarBibliografiaPorISBN(Long isbn) {
        Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbnAndEliminadaTrue(isbn);

        bibliografiaOptional.ifPresent(bibliografia -> {
            bibliografia.setEliminada(false);
            bibliografiaRepository.save(bibliografia);
        });
        String mensaje = bibliografiaOptional.isPresent() ? "Bibliografía recuperada correctamente." : "No se encontró bibliografía con el ISBN proporcionado.";
        return new MensajeDTO(mensaje);
    }



    private Bibliografia convertirDtoAEntidad(BibliografiaDTO bibliografiaDTO) {
        Bibliografia bibliografia = new Bibliografia();
        bibliografia.setTitulo(bibliografiaDTO.getTitulo());
        bibliografia.setNombreAutor(bibliografiaDTO.getNombreAutor());
        bibliografia.setApellidoAutor(bibliografiaDTO.getApellidoAutor());
        bibliografia.setAnioPublicacion(bibliografiaDTO.getAnioPublicacion());
        bibliografia.setIsbn(bibliografiaDTO.getIsbn());
        bibliografia.setIssn(bibliografiaDTO.getIssn());
        bibliografia.setMonto(bibliografiaDTO.getMonto());
        bibliografia.setEliminada(false);

        return bibliografia;
    }


    public void removerBibliografia(Long id) {
        bibliografiaRepository.deleteById(id);
    }

    //public List<Bibliografia> buscarPorTitulo(String titulo) {
   //     return bibliografiaRepository.findByTitulo(titulo);
    //}

    public MensajeDTO buscarPorTitulo(String titulo) {
        List<Bibliografia> bibliografias = bibliografiaRepository.findByTitulo(titulo);

        if (!bibliografias.isEmpty()) {;
            String mensaje = "Bibliografía encontrada con el título: " + titulo;
            return new MensajeDTO(mensaje);
        } else {
            String mensaje = "No se encontró bibliografía con el título: " + titulo;
            return new MensajeDTO(mensaje);
        }
    }

    public Optional<Bibliografia> buscarPorApellidoAutor(String apellidoAutor) {
        return bibliografiaRepository.findByApellidoAutor(apellidoAutor);
    }

    public List<Bibliografia> listarTodas() {
        return bibliografiaRepository.findByEliminadaFalse();
    }

}

