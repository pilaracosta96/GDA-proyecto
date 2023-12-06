package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.entities.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoMaterialRepository extends JpaRepository<TipoMaterial, Long> {
    Optional<TipoMaterial> findByNombreTipoMaterial(String nombreTipoMaterial);
}
