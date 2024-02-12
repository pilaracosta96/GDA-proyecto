package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BibliografiaRepository extends JpaRepository<Bibliografia, Long> {
    Optional<Bibliografia> findByIsbn (Long isbn);

    @Query("SELECT b FROM Bibliografia b WHERE b.titulo = ?1 AND b.eliminada = false")
    List<Bibliografia> findByTitulo(String titulo);

    @Query("SELECT b FROM Bibliografia b WHERE b.apellidoAutor = ?1 AND b.eliminada = false")
    List<Bibliografia> busqapellido nofindByApellidoAutor(String apellidoAutor);
    List<Bibliografia> findByEditorial(Editorial editorial);

    Optional<Bibliografia> findByIsbnAndEliminadaTrue(Long isbn);

    List<Bibliografia> findAllByEliminada(boolean b);
}
