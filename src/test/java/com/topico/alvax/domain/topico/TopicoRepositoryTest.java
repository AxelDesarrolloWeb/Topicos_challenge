package com.topico.alvax.domain.topicos;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Deberia devolver null cuando el topico buscado existe pero no esta disponible en esa fecha")
    void elegirTopicoAleatorioDisponibleEnLaFechaEscenario1() {
        //given o arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var topico = registrarTopico("Topico1", "topico@gmail.com", "12345", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Paciente1", "paciente@gmail.com", "123789");
        registrarConsulta(topico, paciente, lunesSiguienteALas10);
        //when o act
        var topicoLibre = topicoRepository.elegirTopicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        //then o assert
        assertThat(topicoLibre).isNull();
    }

    @Test
    @DisplayName("Deberia devolver topico cuando el topico buscado esta disponible en esa fecha")
    void elegirTopicoAleatorioDisponibleEnLaFechaEscenario2() {
        //given o arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var topico = registrarTopico("Topico1", "topico@gmail.com", "12345", Especialidad.CARDIOLOGIA);
        //when o act
        var topicoLibre = topicoRepository.elegirTopicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        //then o assert
        assertThat(topicoLibre).isEqualTo(topico);
    }

    private void registrarConsulta(Topico topico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, topico, paciente, fecha));
    }

    private Topico registrarTopico(String nombre, String email, String documento, Especialidad especialidad) {
        var topico = new Topico(datosTopico(nombre, email, documento, especialidad));
        em.persist(topico);
        return topico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroTopico datosTopico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroTopico(
                nombre,
                email,
                "6145489789",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "1234564",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle x",
                "distrito y",
                "ciudad z",
                "123",
                "1"
        );
    }
}