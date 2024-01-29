package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.dto.LicenciaSoftwareDTO;
import com.adquisicion_g7.adquisicion.dto.MensajeDTO;
import com.adquisicion_g7.adquisicion.entities.Fabricante;
import com.adquisicion_g7.adquisicion.entities.LicenciaSoftware;
import com.adquisicion_g7.adquisicion.repository.FabricanteRepository;
import com.adquisicion_g7.adquisicion.repository.LicenciaSoftwareRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenciaSoftwareService {
    private final LicenciaSoftwareRepository licenciaSoftwareRepository;
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public LicenciaSoftwareService(LicenciaSoftwareRepository licenciaSoftwareRepository, FabricanteRepository fabricanteRepository){
        this.licenciaSoftwareRepository = licenciaSoftwareRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    @Transactional
    public MensajeDTO guardarLicenciaSoftware (LicenciaSoftwareDTO licenciaSoftwareDTO){
        String nombreFabricante = licenciaSoftwareDTO.getFabricante().toUpperCase();

    // Verificar y guardar el Fabricante
    Fabricante fabricante = fabricanteRepository.findByNombreFabricante(nombreFabricante)
        .orElseGet(() -> fabricanteRepository.save(new Fabricante(nombreFabricante)));

    // Crear la nueva LicenciaSoftware y asignarle el Fabricante
    LicenciaSoftware nuevaLicenciaSoftware = convertirDtoAEntidad(licenciaSoftwareDTO);
        nuevaLicenciaSoftware.setFabricante(fabricante);
        nuevaLicenciaSoftware.setEliminada(false);

    // Guardar la LicenciaSoftware
    licenciaSoftwareRepository.save(nuevaLicenciaSoftware);
    return new MensajeDTO("LicenciaSoftware guardada correctamente");


    }

    private LicenciaSoftware convertirDtoAEntidad(LicenciaSoftwareDTO licenciaSoftwareDTO) {
        LicenciaSoftware licenciaSoftware = new LicenciaSoftware();
        licenciaSoftware.setNombre(licenciaSoftwareDTO.getNombre());
        licenciaSoftware.setAnio(licenciaSoftwareDTO.getAnio());
        licenciaSoftware.setFechaOtorgamiento(licenciaSoftwareDTO.getFechaOtorgamiento());
        licenciaSoftware.setFechaVencimiento(licenciaSoftwareDTO.getFechaVencimiento());
        licenciaSoftware.setNumeroRelease(licenciaSoftwareDTO.getNumeroRelease());
        licenciaSoftware.setVersion(licenciaSoftwareDTO.getVersion());
        licenciaSoftware.setMonto(licenciaSoftwareDTO.getMonto());
        licenciaSoftware.setEliminada(false);

        return licenciaSoftware;

    }

    public void removerLicenciaSoftware(Long id) {
        licenciaSoftwareRepository.deleteById(id);
    }
    public MensajeDTO buscarPorNombre(String nombre) {
        List<LicenciaSoftware> licenciaSoftwares = licenciaSoftwareRepository.findByNombre(nombre);
        if (!licenciaSoftwares.isEmpty()){
            String mensaje = "LicenciaSoftware encontrada con el nombre: " + nombre;
            return new MensajeDTO(mensaje);
        } else {
            String mensaje = "No se encontró LicenciaSoftware con el nombre: " + nombre;
            return new MensajeDTO(mensaje);
        }
    }
    public List<LicenciaSoftware> listarTodas() {
        return licenciaSoftwareRepository.findAllByEliminada(false);
    }

    public MensajeDTO buscarNombreFabricante(String nombreFabricante) {
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findByNombreFabricante(nombreFabricante);

        if (fabricanteOptional.isPresent()) {
            Fabricante fabricante = fabricanteOptional.get();
            String mensaje = " El fabricante " + nombreFabricante + " existe en la base.";
            return new MensajeDTO(mensaje);
        } else {
            String mensaje = "No se encontró el fabricante " + nombreFabricante;
            return new MensajeDTO(mensaje);
        }
    }

        @Transactional
        public MensajeDTO eliminarLicenciaSoftwarePorId(Long id) {
            Optional<LicenciaSoftware> licenciaSoftwareOptional= licenciaSoftwareRepository.findById(id);
            licenciaSoftwareOptional.ifPresent(licenciaSoftware -> {
                licenciaSoftware.setEliminada(true);
                licenciaSoftwareRepository.save(licenciaSoftware);
            });
            String mensaje = licenciaSoftwareOptional.isPresent() ? "LicenciaSoftware eliminada correctamente. " : "No se encontró bibliografía con el ISBN proporcionado. ";
            return new MensajeDTO(mensaje);
        }
        @Transactional
        public MensajeDTO recuperarLicenciaSoftwarePorId(Long id) {
            Optional<LicenciaSoftware> licenciaSoftwareOptional = licenciaSoftwareRepository.findByIdAndEliminadaTrue(id);
            licenciaSoftwareOptional.ifPresent(licenciaSoftware -> {
                licenciaSoftware.setEliminada(false);
                licenciaSoftwareRepository.save(licenciaSoftware);
            });
            String mensaje = licenciaSoftwareOptional.isPresent() ? "LicenciaSoftware recuperada correctamente." : "No se encontró LicenciaSoftware con el ID proporcionado.";
            return new MensajeDTO(mensaje);

        }

    }

