package com.adquisicion_g7.adquisicion.repository;


import com.adquisicion_g7.adquisicion.entities.EquipoInfraestructura;
import com.adquisicion_g7.adquisicion.entities.Servicio;
import com.adquisicion_g7.adquisicion.entities.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoInfraestructuraRepository extends JpaRepository<EquipoInfraestructura, Long>{

    //List<EquipoInfraestructura> findByDenominacion(TipoEquipo tipoEquipo);

    Optional<EquipoInfraestructura> findByIdAndEliminadaTrue(Long id);
    List<EquipoInfraestructura> findAllByEliminada(boolean b);

    boolean existsByNumeroSerieAndEliminadaFalse(String numeroSerie);

    Optional<EquipoInfraestructura> findByNumeroSerieAndEliminadaTrue(String numeroSerie);

    Optional<EquipoInfraestructura> findByNumeroSerieAndEliminadaFalse(String numeroSerie);

}
