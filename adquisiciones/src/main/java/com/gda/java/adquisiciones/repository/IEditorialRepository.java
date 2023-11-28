package com.gda.java.adquisiciones.repository;

import com.gda.java.adquisiciones.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEditorialRepository extends JpaRepository<Editorial, Long> {
    List<Editorial> findByNombreEditorial(String nombreEditorial);
}
