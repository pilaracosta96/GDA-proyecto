package com.gda.java.adquisiciones.controller;

import com.gda.java.adquisiciones.dto.BibliografiaDTO;
import com.gda.java.adquisiciones.entity.Bibliografia;
import com.gda.java.adquisiciones.services.BibliografiaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("bibliografia")
public class BibliografiaController {

    private final BibliografiaService bibliografiaService;
    private final ModelMapper modelMapper;

    public BibliografiaController(BibliografiaService bibliografiaService, ModelMapper modelMapper) {
        this.bibliografiaService = bibliografiaService;
        this.modelMapper = modelMapper;
    }
/*
    @GetMapping("/consultas")
    public ResponseEntity<List<BibliografiaDTO>> obtenerTodasBibliografias() {
        List<BibliografiaDTO> bibliografias = bibliografiaService.obtenerTodasBibliografias();
        List<BibliografiaDTO> bibliografiasDTO = convertirBibliografiasaDTOs(bibliografias);
        return new ResponseEntity<>(bibliografiasDTO, HttpStatus.OK);
    }*/
@GetMapping("/consultas")
public ResponseEntity<List<BibliografiaDTO>> obtenerTodasBibliografias() {
    List<BibliografiaDTO> bibliografiasDTO = bibliografiaService.obtenerTodasBibliografias();
    return new ResponseEntity<>(bibliografiasDTO, HttpStatus.OK);
}

    @PostMapping("/guardar")
    public ResponseEntity<BibliografiaDTO> guardarBibliografia(@RequestBody BibliografiaDTO bibliografiaDTO) {
        Bibliografia bibliografia = convertirDTOaBibliografia(bibliografiaDTO);
        bibliografiaService.guardarBibliografia(bibliografia);

        // Puedes devolver la bibliografía DTO recién creada después de guardarla
        return new ResponseEntity<>(bibliografiaDTO, HttpStatus.CREATED);
    }

    // Otros métodos según sea necesario

    private List<BibliografiaDTO> convertirBibliografiasaDTOs(List<Bibliografia> bibliografias) {
        return bibliografias.stream()
                .map(this::convertirBibliografiaaDTO)
                .collect(Collectors.toList());
    }


    private BibliografiaDTO convertirBibliografiaaDTO(Bibliografia bibliografia) {
        BibliografiaDTO bibliografiaDTO = modelMapper.map(bibliografia, BibliografiaDTO.class);

        bibliografiaDTO.setEditorial(bibliografia.getEditorial().getNombreEditorial());
        bibliografiaDTO.setTipoMaterial(bibliografia.getTipoMaterial().getNombreTipoMaterial());

        return bibliografiaDTO;
    }

    private Bibliografia convertirDTOaBibliografia(BibliografiaDTO bibliografiaDTO) {
        Bibliografia bibliografia = modelMapper.map(bibliografiaDTO, Bibliografia.class);
        // Asegúrate de manejar la conversión de Editorial y TipoMaterial en el servicio si es necesario
        return bibliografia;
    }
}





/*
package com.gda.java.adquisiciones.controller;

import com.gda.java.adquisiciones.dto.BibliografiaDTO;
import com.gda.java.adquisiciones.entity.Bibliografia;
import com.gda.java.adquisiciones.entity.Editorial;
import com.gda.java.adquisiciones.entity.TipoMaterial;
import com.gda.java.adquisiciones.services.BibliografiaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bibliografia")
public class BibliografiaController {

    private final BibliografiaService bibliografiaService;
    private final ModelMapper modelMapper;
    public BibliografiaController(BibliografiaService bibliografiaService, ModelMapper modelMapper) {
        this.bibliografiaService = bibliografiaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/consultas")
    public ResponseEntity<List<BibliografiaDTO>> obtenerTodasBibliografias() {
        List<BibliografiaDTO> bibliografias = bibliografiaService.obtenerTodasBibliografias();
        List<BibliografiaDTO> bibliografiasDTO = convertirBibliografiasaDTOs(bibliografias);
        return new ResponseEntity<>(bibliografiasDTO, HttpStatus.OK);
    }
    @PostMapping("/guardar")
    public ResponseEntity<BibliografiaDTO> guardarBibliografia(@RequestBody BibliografiaDTO bibliografiaDTO) {
        Bibliografia bibliografia = convertirDTOaBibliografia(bibliografiaDTO);
        bibliografiaService.guardarBibliografia(bibliografia);

        // Puedes devolver la bibliografía DTO recién creada después de guardarla
        return new ResponseEntity<>(bibliografiaDTO, HttpStatus.CREATED);
    }
    // Otros métodos según sea necesario

    private List<BibliografiaDTO> convertirBibliografiasaDTOs(List<Bibliografia> bibliografias) {
        return bibliografias.stream()
                .map(bibliografia -> modelMapper.map(bibliografia, BibliografiaDTO.class))
                .collect(Collectors.toList());
    }

    private BibliografiaDTO convertirBibliografiaaDTO(Bibliografia bibliografia) {
        BibliografiaDTO bibliografiaDTO = new BibliografiaDTO();

        bibliografiaDTO.setTitulo(bibliografia.getTitulo());
        bibliografiaDTO.setNombreAutor(bibliografia.getNombreAutor());
        bibliografiaDTO.setApellidoAutor(bibliografia.getApellidoAutor());
        bibliografiaDTO.setAnioPublicacion(bibliografia.getAnioPublicacion());
        bibliografiaDTO.setEditorial(bibliografia.getEditorial().getNombreEditorial()); // Supongo que Editorial tiene un método getNombre()
        bibliografiaDTO.setIsbn(Long.valueOf(String.valueOf(bibliografia.getIsbn())));
        bibliografiaDTO.setIssn(Long.valueOf(String.valueOf(bibliografia.getIssn())));

        bibliografiaDTO.setMonto((float) bibliografia.getMonto());
        bibliografiaDTO.setTipoMaterial(bibliografia.getTipoMaterial().getNombreTipoMaterial()); // Supongo que TipoMaterial tiene un método getNombre()

        return bibliografiaDTO;
    }

    @PostMapping("/guardar")
    public ResponseEntity<BibliografiaDTO> guardarBibliografia(@RequestBody BibliografiaDTO bibliografiaDTO) {
        Bibliografia bibliografia = convertirDTOaBibliografia(bibliografiaDTO);
        bibliografiaService.guardarBibliografia(bibliografia);

        // Puedes devolver la bibliografía DTO recién creada después de guardarla
        return new ResponseEntity<>(bibliografiaDTO, HttpStatus.CREATED);
    }

    private Bibliografia convertirDTOaBibliografia(BibliografiaDTO bibliografiaDTO) {
        Bibliografia bibliografia = new Bibliografia();

        bibliografia.setTitulo(bibliografiaDTO.getTitulo());
        bibliografia.setNombreAutor(bibliografiaDTO.getNombreAutor());
        bibliografia.setApellidoAutor(bibliografiaDTO.getApellidoAutor());
        bibliografia.setAnioPublicacion(bibliografiaDTO.getAnioPublicacion());

        // Puedes necesitar lógica adicional para convertir nombres de editorial y tipoMaterial a objetos Editorial y TipoMaterial
        bibliografia.setEditorial(new Editorial(bibliografiaDTO.getEditorial()));
        bibliografia.setTipoMaterial(new TipoMaterial(bibliografiaDTO.getTipoMaterial()));

        bibliografia.setIsbn(Long.valueOf(bibliografiaDTO.getIsbn()));
        bibliografia.setIssn(Long.valueOf(bibliografiaDTO.getIssn()));
        bibliografia.setMonto(bibliografiaDTO.getMonto());

        return bibliografia;
    }
}

*/




















