package com.gda.java.adquisiciones.repository;

import com.gda.java.adquisiciones.entity.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITipoMaterialRepository extends JpaRepository<TipoMaterial, Long> {
    List<TipoMaterial> findByNombreTipoMaterial(String nombreTipoMaterial);
}
