package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    Optional<Editorial> findByNombreEditorial(String nombreEditorial);

}
