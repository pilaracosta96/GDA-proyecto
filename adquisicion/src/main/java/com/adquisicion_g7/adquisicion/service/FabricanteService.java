package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.entities.Fabricante;
import com.adquisicion_g7.adquisicion.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }
    public Optional<Fabricante> buscarPorId(Long id) {

        return fabricanteRepository.findById(id);
    }

    public Fabricante guardar(Fabricante fabricante) {

        return fabricanteRepository.save(fabricante);
    }

    public Optional<Fabricante> buscarPorNombre(String nombreFabricante){

        return fabricanteRepository.findByNombreFabricante(nombreFabricante);
    }
}
