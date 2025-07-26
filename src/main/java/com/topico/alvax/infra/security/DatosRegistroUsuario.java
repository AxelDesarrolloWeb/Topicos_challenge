package com.topico.alvax.infra.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank @Email String email,
        @NotBlank String contrasena
) {}