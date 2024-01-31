package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.entities.TipoEquipo;
import com.adquisicion_g7.adquisicion.repository.TipoEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoEquipoService {

    private final TipoEquipoRepository tipoEquipoRepository;

    @Autowired
    public TipoEquipoService(TipoEquipoRepository tipoEquipoRepository) {
        this.tipoEquipoRepository = tipoEquipoRepository;
    }
}