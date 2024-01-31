package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.Servicio;
import com.adquisicion_g7.adquisicion.entities.TipoServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    //Optional<Servicio> findByNombreTipoServicio(String nombreTipoServicio);

    Optional<Servicio> findByIdAndEliminadaTrue(Long id);
    List<Servicio> findAllByEliminada(boolean b);

}
