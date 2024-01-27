package com.adquisicion_g7.adquisicion.service;

import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    public Optional<Editorial> buscarPorId(Long id) {
        return editorialRepository.findById(id);
    }

    public Editorial guardar(Editorial editorial) {
        return editorialRepository.save(editorial);
    }
}
