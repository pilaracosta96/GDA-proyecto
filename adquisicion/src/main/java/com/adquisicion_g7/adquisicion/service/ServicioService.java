package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.dto.ServicioDTO;
import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.entities.Servicio;
import com.adquisicion_g7.adquisicion.entities.TipoServicio;
import com.adquisicion_g7.adquisicion.repository.ServicioRepository;
import com.adquisicion_g7.adquisicion.repository.TipoServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final TipoServicioRepository tipoServicioRepository;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository, TipoServicioRepository tipoServicioRepository) {
        this.servicioRepository = servicioRepository;
        this.tipoServicioRepository = tipoServicioRepository;

    }
    @Transactional
    public MensajeDTO guardarServicio(ServicioDTO servicioDTO) {
        String nombreTipoServicio = servicioDTO.getTipoServicio().toUpperCase();

    // Verificar y guardar el Tipo de Servcio
    TipoServicio tipoServicio = tipoServicioRepository.findByNombreTipoServicio(nombreTipoServicio)
            .orElseGet(() -> tipoServicioRepository.save(new TipoServicio(null, nombreTipoServicio)));


    // Crear el nueva Servicio y asignarle el Tipo de Servicio
        Servicio nuevoServicio = convertirDTOAEntidad(servicioDTO);
        nuevoServicio.setTipoServicio(tipoServicio);
        nuevoServicio.setEliminada(false);

    //Guardar el Servicio
    servicioRepository.save(nuevoServicio);
    return new MensajeDTO("Servicio guardado correctamente");

    }

    private Servicio convertirDTOAEntidad(ServicioDTO servicioDTO){
        Servicio servicio = new Servicio();
        servicio.setFecha(servicioDTO.getFecha());
        servicio.setMonto(servicioDTO.getMonto());
        //servicio.setEliminada(false);

        return servicio;
    }
    public void removerServicio (Long id) { servicioRepository.deleteById(id);}

    public List<Servicio> listarTodas() {return servicioRepository.findAllByEliminada(false);}

    public MensajeDTO buscarNombreTipoServicio(String nombreTipoServicio) {
        Optional<Servicio> servicioOptional = servicioRepository.findByNombreTipoServicio(nombreTipoServicio);

        if (servicioOptional.isPresent()) {
            Servicio servicio = servicioOptional.get();
            String mensaje = " El servicio " + nombreTipoServicio + " existe en la base.";
            return new MensajeDTO(mensaje);
        }else {
            String mensaje = "No se encontró el servicio " + nombreTipoServicio;
            return new MensajeDTO(mensaje);
        }
    }
    @Transactional
    public MensajeDTO eliminarServicioPorId(Long id) {
        Optional<Servicio> servicioOptional = servicioRepository.findById(id);
        servicioOptional.ifPresent(servicio -> {
            servicio.setEliminada(true);
            servicioRepository.save(servicio);
        });
        String mensaje = servicioOptional.isPresent() ? "Servicio eliminado correctamente. " : "No se encontró servcio con el ID proporcionado. ";
        return new MensajeDTO(mensaje);
    }
    @Transactional
    public MensajeDTO recuperarServicioPorId(Long id) {
        Optional<Servicio> servicioOptional = servicioRepository.findByIdAndEliminadaTrue(id);
        servicioOptional.ifPresent(servicio -> {
            servicio.setEliminada(false);
            servicioRepository.save(servicio);
        });
        String mensaje = servicioOptional.isPresent() ? "Servicio recuperado correctamente." : "No se encontró servicio con el ID proporcionado.";
        return new MensajeDTO(mensaje);

    }

}


