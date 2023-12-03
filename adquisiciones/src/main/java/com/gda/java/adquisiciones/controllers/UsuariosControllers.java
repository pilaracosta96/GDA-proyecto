package com.gda.java.adquisiciones.controllers;

import com.gda.java.adquisiciones.models.Usuario;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuariosControllers {

    @RequestMapping( value= "usuario" )
    public Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("pilar");
        usuario.setApelldio("acosta");
        usuario.setEmail("pilaracos@sdd.com");
        usuario.setPassword("1234");
        return usuario;
    }
}
