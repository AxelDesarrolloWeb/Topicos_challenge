package com.topico.alvax.domain.topicos;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,
        @NotBlank
        String curso,
        @NotBlank
        String mensaje
) {}