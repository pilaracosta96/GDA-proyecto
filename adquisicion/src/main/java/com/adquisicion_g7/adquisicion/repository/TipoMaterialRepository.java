package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.TipoMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMaterialRepository extends JpaRepository<TipoMaterial, Long> {

}
