package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.entities.TipoServicio;
import com.adquisicion_g7.adquisicion.repository.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;

    @Autowired
    public TipoServicioService(TipoServicioRepository tipoServicioRepository){
        this.tipoServicioRepository = tipoServicioRepository;
    }

    public Optional<TipoServicio> buscarPorId(Long id) {return tipoServicioRepository.findById(id);}

    public TipoServicio guardar(TipoServicio tipoServicio) { return tipoServicioRepository.save(tipoServicio);}
}
