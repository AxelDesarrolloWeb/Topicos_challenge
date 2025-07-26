package com.topico.alvax.domain.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String curso;
    private String mensaje;
    @Column(name = "fecha_creacion") // Añade esta anotación
    private LocalDateTime fechaCreacion;
    private Boolean status;

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.mensaje = datos.mensaje();
        this.status = true;
        this.fechaCreacion = LocalDateTime.now(); // Genera automáticamente
    }

    public void actualizarInformaciones(DatosActualizarTopico datos) {
        if (datos.curso() != null) {
            this.curso = datos.curso();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
    }

    public void eliminar() {
        this.status = false;
    }
}