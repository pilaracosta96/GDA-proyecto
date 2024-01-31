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
import java.util.NoSuchElementException;
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
        String nombreTipoMaterial = bibliografiaDTO.getTipoMaterial().toUpperCase();
        Long isbn = bibliografiaDTO.getIsbn();

        // Verificar ISBN
        Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbn(isbn);
        if (bibliografiaOptional.isPresent()) {
            String mensaje = "ISBN " + isbn + " ya existe en la base de datos";
            return new MensajeDTO(mensaje);
        }



        // Verificar y guardar el Tipo de Material
        TipoMaterial tipoMaterial = tipoMaterialRepository.findByNombreTipoMaterial(nombreTipoMaterial)
                .orElseGet(() -> tipoMaterialRepository.save(new TipoMaterial(null, nombreTipoMaterial)));

        // Verificar y guardar la Editorial
        Editorial editorial = editorialRepository.findByNombreEditorial(nombreEditorial)
                .orElseGet(() -> editorialRepository.save(new Editorial(nombreEditorial)));

        // Crear la nueva Bibliografía y asignarle la Editorial y el Tipo de Material
        Bibliografia nuevaBibliografia = convertirDtoAEntidad(bibliografiaDTO);
        nuevaBibliografia.setEditorial(editorial);
        nuevaBibliografia.setTipoMaterial(tipoMaterial);
        nuevaBibliografia.setEliminada(false);

        // Guardar la Bibliografía
        bibliografiaRepository.save(nuevaBibliografia);
        return new MensajeDTO("Bibliografía guardada correctamente");
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

    public MensajeDTO buscarPorTitulo(String titulo) {
        List<Bibliografia> bibliografias = bibliografiaRepository.findByTitulo(titulo);
        if (!bibliografias.isEmpty()){
            String mensaje = "Bibliografía encontrada con el título: " + titulo;
            return new MensajeDTO(mensaje);
        } else {
            String mensaje = "No se encontró bibliografía con el título: " + titulo;
            return new MensajeDTO(mensaje);
        }
    }

    public List<Bibliografia> buscarPorApellidoAutor(String apellidoAutor) {
        return bibliografiaRepository.findByApellidoAutor(apellidoAutor);
    }

    public List<Bibliografia> listarTodas() {
        return bibliografiaRepository.findAllByEliminada(false);
    }


    public MensajeDTO buscarNombreEditorial(String nombreEditorial) {
        Optional<Editorial> editorialOptional = editorialRepository.findByNombreEditorial(nombreEditorial);

        if (editorialOptional.isPresent()) {
            Editorial editorial = editorialOptional.get();
            String mensaje = " La editorial " + nombreEditorial + " existe en la base.";
            return new MensajeDTO(mensaje);
        }else {
            String mensaje = "No se encontró la editorial " + nombreEditorial;
            return new MensajeDTO(mensaje);
        }

    }
@Transactional
    public MensajeDTO eliminarBibliografiaPorISBN(Long isbn) {
        Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbn(isbn);
        bibliografiaOptional.ifPresent(bibliografia -> {
            bibliografia.setEliminada(true);
            bibliografiaRepository.save(bibliografia);
        });
        String mensaje = bibliografiaOptional.isPresent() ? "Bibliografía eliminada correctamente. " : "No se encontró bibliografía con el ISBN proporcionado. ";
        return new MensajeDTO(mensaje);
    }
@Transactional
    public MensajeDTO recuperarBibliografiaPorISBN(Long isbn) {
    Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbnAndEliminadaTrue(isbn);
    bibliografiaOptional.ifPresent(bibliografia -> {
        bibliografia.setEliminada(false);
        bibliografiaRepository.save(bibliografia);
    });
    String mensaje = bibliografiaOptional.isPresent() ? "Bibliografía recuperada correctamente." : "No se encontró bibliografía eliminada con el ISBN proporcionado.";
    return new MensajeDTO(mensaje);

    }

}


