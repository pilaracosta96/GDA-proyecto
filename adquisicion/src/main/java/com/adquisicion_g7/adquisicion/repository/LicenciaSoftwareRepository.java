package com.adquisicion_g7.adquisicion.repository;


import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Fabricante;
import com.adquisicion_g7.adquisicion.entities.LicenciaSoftware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LicenciaSoftwareRepository extends JpaRepository<LicenciaSoftware, Long> {
    List<LicenciaSoftware> findByNombre(String nombre);

    Optional<LicenciaSoftware> findByIdAndEliminadaTrue(Long id);

    List<LicenciaSoftware> findByFabricante(Fabricante fabricante);
    //Optional<LicenciaSoftware> findByAnio (Long anio);
    List<LicenciaSoftware> findAllByEliminada(boolean b);
}
