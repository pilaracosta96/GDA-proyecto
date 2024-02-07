package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.dto.EquipoInfraestructuraDTO;
import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.entities.EquipoInfraestructura;
import com.adquisicion_g7.adquisicion.repository.*;
import com.adquisicion_g7.adquisicion.entities.TipoEquipo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoInfraestructuraService {

    private final EquipoInfraestructuraRepository equipoInfraestructuraRepository;

    private final TipoEquipoRepository tipoEquipoRepository;

    @Autowired
    public EquipoInfraestructuraService(EquipoInfraestructuraRepository equipoInfraestructuraRepository, TipoEquipoRepository tipoEquipoRepository) {
        this.equipoInfraestructuraRepository = equipoInfraestructuraRepository;
        this.tipoEquipoRepository = tipoEquipoRepository;
    }

    @Transactional
    public MensajeDTO guardarEquipoInfraestructura(EquipoInfraestructuraDTO equipoInfraestructuraDTO) {
        String nombreTipoEquipo = equipoInfraestructuraDTO.getTipoEquipo().toUpperCase();

        // Verificar y guardar el Tipo de Equipo
        TipoEquipo tipoEquipo = tipoEquipoRepository.findByNombreTipoEquipo(nombreTipoEquipo)
                .orElseGet(() -> tipoEquipoRepository.save(new TipoEquipo(null, nombreTipoEquipo)));


        // Crear el nueva Servicio y asignarle el Tipo de Equipo
        EquipoInfraestructura nuevoEquipoInfraestructura = convertirDTOAEntidad(equipoInfraestructuraDTO);
        nuevoEquipoInfraestructura.setTipoEquipo(tipoEquipo);
        nuevoEquipoInfraestructura.setEliminada(false);

        //Guardar el Servicio
        equipoInfraestructuraRepository.save(nuevoEquipoInfraestructura);
        return new MensajeDTO("Equipo guardado correctamente");

    }

    private EquipoInfraestructura convertirDTOAEntidad(EquipoInfraestructuraDTO equipoInfraestructuraDTO) {
        EquipoInfraestructura equipoInfraestructura = new EquipoInfraestructura();
        equipoInfraestructura.setFechaIncorporacion(equipoInfraestructuraDTO.getFechaIncorporacion());
        equipoInfraestructura.setMonto(equipoInfraestructuraDTO.getMonto());
        equipoInfraestructura.setDescripcion(equipoInfraestructuraDTO.getDescripcion());
        equipoInfraestructura.setNumeroSerie(equipoInfraestructuraDTO.getNumeroSerie());

        // asignamos el TipoEquipo al nuevo equipoInfraestructura
        String nombreTipoEquipo = equipoInfraestructuraDTO.getTipoEquipo().toUpperCase();
        TipoEquipo tipoEquipo = tipoEquipoRepository.findByNombreTipoEquipo(nombreTipoEquipo)
                .orElseGet(() -> tipoEquipoRepository.save(new TipoEquipo(null, nombreTipoEquipo)));
        equipoInfraestructura.setTipoEquipo(tipoEquipo);


        equipoInfraestructura.setEliminada(false);

        return equipoInfraestructura;
    }


    public void removerEquipoInfraestructura (Long id) { equipoInfraestructuraRepository.deleteById(id);}

    //public static List<EquipoInfraestructura> listarTodas() {return EquipoInfraestructuraRepository.findAllByEliminada(false);}

    public List<EquipoInfraestructura> listarTodas() {return equipoInfraestructuraRepository.findAllByEliminada(false);}

    public MensajeDTO buscarNombreTipoEquipo(String nombreTipoEquipo) {
        Optional<TipoEquipo> tipoEquipoOptional = tipoEquipoRepository.findByNombreTipoEquipo(nombreTipoEquipo);

        if (tipoEquipoOptional.isPresent()) {
            TipoEquipo tipoEquipo = tipoEquipoOptional.get();
            String mensaje = " El equipo " + nombreTipoEquipo + " existe en la base.";
            return new MensajeDTO(mensaje);
        } else {
            String mensaje = "No se encontró el equipo " + nombreTipoEquipo;
            return new MensajeDTO(mensaje);
        }
    }

    @Transactional
    public MensajeDTO eliminarEquipoPorId(Long id) {
        Optional<EquipoInfraestructura> equipoInfraestructuraOptional = equipoInfraestructuraRepository.findById(id);
        equipoInfraestructuraOptional.ifPresent(equipoInfraestructura -> {
            equipoInfraestructura.setEliminada(true);
            equipoInfraestructuraRepository.save(equipoInfraestructura);
        });
        String mensaje = equipoInfraestructuraOptional.isPresent() ? "Equipo eliminado correctamente. " : "No se encontró equipo con el ID proporcionado. ";
        return new MensajeDTO(mensaje);
    }
    @Transactional
    public MensajeDTO recuperarEquipoPorId(Long id) {
        Optional<EquipoInfraestructura> equipoInfraestructuraOptional = equipoInfraestructuraRepository.findByIdAndEliminadaTrue(id);
        equipoInfraestructuraOptional.ifPresent(equipoInfraestructura -> {
            equipoInfraestructura.setEliminada(false);
            equipoInfraestructuraRepository.save(equipoInfraestructura);
        });
        String mensaje = equipoInfraestructuraOptional.isPresent() ? "Equipo recuperado correctamente." : "No se encontró el Equipo eliminado con el ID proporcionado.";
        return new MensajeDTO(mensaje);

    }
}
