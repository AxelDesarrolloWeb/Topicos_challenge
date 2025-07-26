package com.topico.alvax.domain.controller;

import med.voll.api.domain.topico.DatosDetalleTopico;
import med.voll.api.domain.topico.DatosReservaTopico;
import med.voll.api.domain.topico.ReservaDeTopicos;
import med.voll.api.domain.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class TopicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosReservaTopico> datosReservaTopicoJson;

    @Autowired
    private JacksonTester<DatosDetalleTopico> datosDetalleTopicoJson;

    @MockBean
    private ReservaDeTopicos reservaDeTopicos;

    @Test
    @DisplayName("Deberia devolver http 400 cuando la request no tenga datos")
    @WithMockUser
    void reservar_escenario1() throws Exception {
        var response = mvc.perform(post("/topicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia devolver http 200 cuando la request reciba un json valido")
    @WithMockUser
    void reservar_escenario2() throws Exception {

        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.CARDIOLOGIA;
        var datosDetalle = new DatosDetalleTopico(null, 2L, 5L, fecha);
        when(reservaDeTopicos.reservar(any())).thenReturn(datosDetalle);

        var response = mvc.perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosReservaTopicoJson.write(
                                        new DatosReservaTopico(2L, 5L, fecha, especialidad)
                                ).getJson()
                        )
                )
                .andReturn().getResponse();

        var jsonEsperado = datosDetalleTopicoJson.write(
                datosDetalle
        ).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}