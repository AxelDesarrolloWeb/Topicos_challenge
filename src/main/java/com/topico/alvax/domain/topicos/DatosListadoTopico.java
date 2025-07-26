package com.topico.alvax.domain.topicos;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id, String mensaje, String titulo, LocalDateTime fechaCreacion) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getMensaje(), topico.getTitulo(), topico.getFechaCreacion());
    }
}