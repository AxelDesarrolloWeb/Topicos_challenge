package com.topico.alvax.domain.topicos;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String autor,
        String curso,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus()
        );
    }
}