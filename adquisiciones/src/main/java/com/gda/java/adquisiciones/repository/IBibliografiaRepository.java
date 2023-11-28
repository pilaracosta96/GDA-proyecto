package com.gda.java.adquisiciones.repository;

import com.gda.java.adquisiciones.entity.Bibliografia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBibliografiaRepository extends JpaRepository<Bibliografia, Long> {
    Optional<Bibliografia> findByIsbn(Long isbn);
}
