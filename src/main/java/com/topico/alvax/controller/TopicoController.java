package com.topico.alvax.controller;

import com.topico.alvax.domain.topicos.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity; // Importación añadida
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        repository.save(new Topico(datos));
    }

    @GetMapping
    public Page<DatosListadoTopico> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosListadoTopico::new);
    }

    // Nuevo endpoint para detalle
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallar(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = repository.getReferenceById(datos.id());
        topico.actualizarInformaciones(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        topico.eliminar();
    }
}