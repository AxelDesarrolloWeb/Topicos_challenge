package com.topico.alvax.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id, String curso, String mensaje, String titulo) {
}
