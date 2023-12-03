package com.adquisicion_g7.adquisicion.service;
import com.adquisicion_g7.adquisicion.entities.TipoMaterial;
import com.adquisicion_g7.adquisicion.repository.TipoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoMaterialService {

    private final TipoMaterialRepository tipoMaterialRepository;

    @Autowired
    public TipoMaterialService(TipoMaterialRepository tipoMaterialRepository) {
        this.tipoMaterialRepository = tipoMaterialRepository;
    }

    public Optional<TipoMaterial> buscarPorId(Long id) {
        return tipoMaterialRepository.findById(id);
    }

    public TipoMaterial guardar(TipoMaterial tipoMaterial) {
        return tipoMaterialRepository.save(tipoMaterial);
    }
}