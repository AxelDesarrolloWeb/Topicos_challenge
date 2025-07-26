package com.topico.alvax.domain.usuarios;

import com.topico.alvax.infra.security.DatosRegistroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(DatosRegistroUsuario datos) {
        String encryptedPassword = passwordEncoder.encode(datos.contrasena());
        Usuario usuario = new Usuario(datos.email(), encryptedPassword);
        usuarioRepository.save(usuario);
    }
}