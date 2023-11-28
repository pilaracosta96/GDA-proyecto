package com.gda.java.adquisiciones.services;

import com.gda.java.adquisiciones.dto.BibliografiaDTO;
import com.gda.java.adquisiciones.entity.Bibliografia;
import com.gda.java.adquisiciones.entity.Editorial;
import com.gda.java.adquisiciones.entity.TipoMaterial;
import com.gda.java.adquisiciones.exceptions.GuardarBibliografiaException;
import com.gda.java.adquisiciones.repository.IBibliografiaRepository;
import com.gda.java.adquisiciones.repository.IEditorialRepository;
import com.gda.java.adquisiciones.repository.ITipoMaterialRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BibliografiaService {

    private final IBibliografiaRepository bibliografiaRepository;
    private final IEditorialRepository editorialRepository;
    private final ITipoMaterialRepository tipoMaterialRepository;
    private final ModelMapper modelMapper;

    public BibliografiaDTO guardarBibliografia(Bibliografia bibliografiaDTO) {
        try {
            final String titulo = bibliografiaDTO.getTitulo();
            final String nombreAutor = bibliografiaDTO.getNombreAutor();
            final String apellidoAutor = bibliografiaDTO.getApellidoAutor();
            final Integer anioPublicacion = bibliografiaDTO.getAnioPublicacion();
            final String nombreEditorial = String.valueOf(bibliografiaDTO.getEditorial());
            final Long isbn = bibliografiaDTO.getIsbn();
            final Long issn = bibliografiaDTO.getIssn();
            final double monto = bibliografiaDTO.getMonto();
            final String nombreTipoMaterial = String.valueOf(bibliografiaDTO.getTipoMaterial());

            final Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbn(isbn);

            if (bibliografiaOptional.isPresent()) {
                throw new GuardarBibliografiaException("La bibliografía ya existe");
            }

            // Crear instancias de Editorial y TipoMaterial
            Editorial editorial = editorialRepository.save(new Editorial(nombreEditorial));
            TipoMaterial tipoMaterial = tipoMaterialRepository.save(new TipoMaterial(nombreTipoMaterial));
            Bibliografia bibliografiaGuardada = bibliografiaRepository.save(new Bibliografia(titulo, nombreAutor, apellidoAutor, anioPublicacion, editorial, isbn, issn, monto, tipoMaterial));

            BibliografiaDTO bibliografiaGuardadaDTO = modelMapper.map(bibliografiaGuardada, BibliografiaDTO.class);
            return bibliografiaGuardadaDTO;
        } catch (DataIntegrityViolationException e) {
            throw new GuardarBibliografiaException("Error de integridad al guardar la bibliografía");
        } catch (Exception e) {
            throw new GuardarBibliografiaException(String.format("Error al guardar la bibliografía: %s", e.getMessage()));
        }
    }

    public List<BibliografiaDTO> obtenerTodasBibliografias() {
        List<Bibliografia> bibliografias = bibliografiaRepository.findAll();
        return convertirBibliografiasaDTOs(bibliografias);
    }


    private List<BibliografiaDTO> convertirBibliografiasaDTOs(List<Bibliografia> bibliografias) {
        return bibliografias.stream()
                .map(bibliografia -> modelMapper.map(bibliografia, BibliografiaDTO.class))
                .collect(Collectors.toList());
    }
}



/*package com.gda.java.adquisiciones.services;

//import com.gda.java.adquisiciones.dto.BibliografiaDTO;
import com.gda.java.adquisiciones.controller.BibliografiaController;
import com.gda.java.adquisiciones.dto.BibliografiaDTO;
import com.gda.java.adquisiciones.entity.Bibliografia;
import com.gda.java.adquisiciones.entity.Editorial;
import com.gda.java.adquisiciones.entity.TipoMaterial;
import com.gda.java.adquisiciones.exceptions.GuardarBibliografiaException;
import com.gda.java.adquisiciones.repository.IBibliografiaRepository;
import com.gda.java.adquisiciones.repository.IEditorialRepository;
import com.gda.java.adquisiciones.repository.ITipoMaterialRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BibliografiaService {

    private final IBibliografiaRepository bibliografiaRepository;
    private final IEditorialRepository editorialRepository;
    private final ITipoMaterialRepository tipoMaterialRepository;
    private final ModelMapper modelMapper;

    public BibliografiaDTO guardarBibliografia(Bibliografia bibliografia) {
        try {
            final String titulo = bibliografia.getTitulo();
            final String nombreAutor = bibliografia.getNombreAutor();
            final String apellidoAutor = bibliografia.getApellidoAutor();
            final Integer anioPublicacion = bibliografia.getAnioPublicacion();
            final String nombreEditorial = String.valueOf(bibliografia.getEditorial());  // Cambio aquí
            final String isbn = bibliografia.getIsbn().toString();
            final String issn = bibliografia.getIssn().toString();
            final double monto = bibliografia.getMonto();
            final String nombreTipoMaterial = String.valueOf(bibliografia.getTipoMaterial());  // Cambio aquí

            final Optional<Bibliografia> bibliografiaOptional = bibliografiaRepository.findByIsbn(isbn);

            if (bibliografiaOptional.isPresent()) {
                throw new GuardarBibliografiaException("La bibliografía ya existe");
            }

            // Crear instancias de Editorial y TipoMaterial
            //final Editorial editorial = new Editorial(nombreEditorial);
            //final TipoMaterial tipoMaterial = new TipoMaterial(nombreTipoMaterial);

            // Crear instancia de Bibliografia
            // final Bibliografia bibliografia = new Bibliografia(titulo, nombreAutor, apellidoAutor, anioPublicacion, editorial, isbn, issn, monto, tipoMaterial);

            // Guardar en el repositorio

            Editorial editorial = editorialRepository.save(new Editorial(nombreEditorial));
            TipoMaterial tipoMaterial = tipoMaterialRepository.save(new TipoMaterial(nombreTipoMaterial));
            Bibliografia bibliografiaGuardada= bibliografiaRepository.save(new Bibliografia(titulo, nombreAutor, apellidoAutor, anioPublicacion, editorial, isbn, issn, monto, tipoMaterial));


            BibliografiaDTO bibliografiaDTO = new BibliografiaDTO();
            bibliografiaDTO.setTitulo(bibliografiaGuardada.getTitulo());
            bibliografiaDTO.setNombreAutor(bibliografiaGuardada.getNombreAutor());
            bibliografiaDTO.setApellidoAutor(bibliografiaGuardada.getApellidoAutor());
            bibliografiaDTO.setAnioPublicacion(bibliografiaGuardada.getAnioPublicacion());
            bibliografiaDTO.setEditorial(editorial.getId());
            bibliografiaDTO.setIsbn(Long.valueOf(isbn));
            bibliografiaDTO.setIssn(Long.valueOf(issn));
            bibliografiaDTO.setMonto((float) monto);
            bibliografiaDTO.setTipomaterial(tipoMaterial.getId());

            return bibliografiaDTO;
        } catch (Exception e) {
            throw new GuardarBibliografiaException(String.format("Error al guardar la bibliografía: %s", e.getMessage()));
        }
    }
}
/*
    public List<BibliografiaDTO> obtenerTodasBibliografias() {
        try {
            List<Bibliografia> bibliografias = bibliografiaRepository.findAll();
            return convertirBibliografiasaDTOs(bibliografias);
        } catch (Exception e) {
            throw new ObtenerBibliografiasException(String.format("Error al obtener las bibliografías: %s", e.getMessage()));
        }
    }*/
/*
    private List<BibliografiaDTO> convertirBibliografiasaDTOs(List<Bibliografia> bibliografias) {
        return bibliografias.stream()
                .map(this::convertirBibliografiaaDTO)
                .collect(Collectors.toList());
    }
    // ...*/


