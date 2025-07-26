package com.topico.alvax.controller;

import com.topico.alvax.domain.usuarios.UsuarioService;
import com.topico.alvax.infra.security.DatosRegistroUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public void registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        usuarioService.registrarUsuario(datos);
    }
}
