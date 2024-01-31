package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Long> {
    Optional<TipoServicio> findByNombreTipoServicio(String nombreTipoServicio);
}
