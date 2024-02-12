package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoEquipoRepository extends JpaRepository<TipoEquipo, Long> {
    Optional<TipoEquipo> findByNombreTipoEquipo(String nombreTipoEquipo);
}
