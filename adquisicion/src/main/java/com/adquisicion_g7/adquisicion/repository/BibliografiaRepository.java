package com.adquisicion_g7.adquisicion.repository;

import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface BibliografiaRepository extends JpaRepository<Bibliografia, Long> {
    public Bibliografia findByIsbn (Long isbn);

 List<Bibliografia> findByTitulo(String titulo);

    List<Bibliografia> findByApellidoAutor(String apellidoAutor);
    List<Bibliografia> findByEditorial(Editorial editorial);
}
