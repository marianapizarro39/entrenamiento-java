package co.ceiba.gestionpersonas.service;

import co.ceiba.gestionpersonas.domain.Persona;
import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;
import co.ceiba.gestionpersonas.exception.EmailYaExisteException;
import co.ceiba.gestionpersonas.exception.PersonaNoEncontradaException;
import co.ceiba.gestionpersonas.repository.PersonaRepository;
import co.ceiba.gestionpersonas.service.impl.PersonaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Test
    void crear_conDatosValidos_guardaYRetornaLaPersona() {
        PersonaRequest request = new PersonaRequest(
                "Mariana", "Pizarro", "mariana@correo.com", LocalDate.of(2006, 10, 14));

        when(personaRepository.existsByEmail("mariana@correo.com")).thenReturn(false);
        when(personaRepository.save(any(Persona.class))).thenAnswer(invocacion -> {
            Persona p = invocacion.getArgument(0);
            p.setId(1L);
            return p;
        });

        PersonaResponse respuesta = personaService.crear(request);

        assertNotNull(respuesta.id());
        assertEquals(1L, respuesta.id());
        assertEquals("Mariana", respuesta.nombre());
        assertEquals("mariana@correo.com", respuesta.email());
        verify(personaRepository).save(any(Persona.class));
    }

    @Test
    void crear_conEmailRepetido_lanzaEmailYaExisteYNoGuarda() {
        PersonaRequest request = new PersonaRequest(
                "Mariana", "Pizarro", "mariana@correo.com", LocalDate.of(2006, 10, 14));

        when(personaRepository.existsByEmail("mariana@correo.com")).thenReturn(true);

        assertThrows(EmailYaExisteException.class, () -> personaService.crear(request));
        verify(personaRepository, never()).save(any(Persona.class));
    }

    @Test
    void crear_conEmailEnMayusculas_loGuardaEnMinusculas() {
        PersonaRequest request = new PersonaRequest(
                "Mariana", "Pizarro", " Mariana@Correo.com ", LocalDate.of(2006, 10, 14));

        when(personaRepository.existsByEmail("mariana@correo.com")).thenReturn(false);
        when(personaRepository.save(any(Persona.class))).thenAnswer(invocacion -> invocacion.getArgument(0));

        PersonaResponse respuesta = personaService.crear(request);

        assertEquals("mariana@correo.com", respuesta.email());
    }

    @Test
    void listar_conDatos_retornaDtosEnElOrdenDelRepositorio() {
        when(personaRepository.findAllByOrderByApellidoAsc()).thenReturn(List.of(
                new Persona(2L, "Camila", "Ossa", "camila@correo.com", LocalDate.of(2000, 7, 15)),
                new Persona(1L, "Santiago", "Cano", "cano@correo.com", LocalDate.of(2006, 8, 18))));

        List<PersonaResponse> respuestas = personaService.listar();

        assertEquals(2, respuestas.size());
        PersonaResponse primero = respuestas.get(0);
        assertEquals(2L, primero.id());
        assertEquals("Camila", primero.nombre());
        assertEquals("Ossa", primero.apellido());
        assertEquals("camila@correo.com", primero.email());
        assertEquals(LocalDate.of(2000, 7, 15), primero.fechaNacimiento());
        assertEquals("Santiago", respuestas.get(1).nombre());
        verify(personaRepository).findAllByOrderByApellidoAsc();
    }

    @Test
    void listar_sinDatos_retornaListaVacia() {
        when(personaRepository.findAllByOrderByApellidoAsc()).thenReturn(List.of());

        assertTrue(personaService.listar().isEmpty());
    }

    @Test
    void buscarPorId_existe_retornaLaPersonaMapeada() {
        when(personaRepository.findById(1L)).thenReturn(Optional.of(
                new Persona(1L, "Camila", "Ossa", "camila@correo.com", LocalDate.of(2000, 7, 15))));

        PersonaResponse respuesta = personaService.buscarPorId(1L);

        assertEquals(1L, respuesta.id());
        assertEquals("Camila", respuesta.nombre());
        assertEquals("Ossa", respuesta.apellido());
        assertEquals("camila@correo.com", respuesta.email());
        assertEquals(LocalDate.of(2000, 7, 15), respuesta.fechaNacimiento());
        verify(personaRepository).findById(1L);
    }

    @Test
    void buscarPorId_noExiste_lanzaPersonaNoEncontrada() {
        when(personaRepository.findById(99L)).thenReturn(Optional.empty());

        PersonaNoEncontradaException ex = assertThrows(PersonaNoEncontradaException.class, () -> personaService.buscarPorId(99L));
        assertEquals("No existe una persona con el id 99", ex.getMessage());
        verify(personaRepository).findById(99L);
    }

    @Test
    void actualizar_conDatosValidos_actualizaYRetornaLaPersona() {
        Persona existente = new Persona(1L, "Camila", "Ossa", "camila@correo.com", LocalDate.of(2000, 7, 15));
        PersonaRequest request = new PersonaRequest(
                "Camila Andrea", "Ossa Ruiz", " Camila.Andrea@Correo.com ", LocalDate.of(2000, 7, 16));

        when(personaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(personaRepository.existsByEmailAndIdNot("camila.andrea@correo.com", 1L)).thenReturn(false);
        when(personaRepository.save(any(Persona.class))).thenAnswer(invocacion -> invocacion.getArgument(0));

        PersonaResponse respuesta = personaService.actualizar(1L, request);

        assertEquals(1L, respuesta.id());
        assertEquals("Camila Andrea", respuesta.nombre());
        assertEquals("Ossa Ruiz", respuesta.apellido());
        assertEquals("camila.andrea@correo.com", respuesta.email());
        assertEquals(LocalDate.of(2000, 7, 16), respuesta.fechaNacimiento());
        verify(personaRepository).save(existente);
    }

    @Test
    void actualizar_noExiste_lanzaPersonaNoEncontradaYNoGuarda() {
        PersonaRequest request = new PersonaRequest(
                "Camila", "Ossa", "camila@correo.com", LocalDate.of(2000, 7, 15));

        when(personaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PersonaNoEncontradaException.class, () -> personaService.actualizar(99L, request));
        verify(personaRepository, never()).save(any(Persona.class));
    }

    @Test
    void actualizar_conEmailDeOtraPersona_lanzaEmailYaExisteYNoGuarda() {
        Persona existente = new Persona(1L, "Camila", "Ossa", "camila@correo.com", LocalDate.of(2000, 7, 15));
        PersonaRequest request = new PersonaRequest(
                "Camila", "Ossa", "santiago@correo.com", LocalDate.of(2000, 7, 15));

        when(personaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(personaRepository.existsByEmailAndIdNot("santiago@correo.com", 1L)).thenReturn(true);

        assertThrows(EmailYaExisteException.class, () -> personaService.actualizar(1L, request));
        verify(personaRepository, never()).save(any(Persona.class));
    }

    @Test
    void eliminar_existe_eliminaLaPersona() {
        Persona existente = new Persona(1L, "Camila", "Ossa", "camila@correo.com", LocalDate.of(2000, 7, 15));
        when(personaRepository.findById(1L)).thenReturn(Optional.of(existente));

            personaService.eliminar(1L);

        verify(personaRepository).delete(existente);
    }

    @Test
    void eliminar_noExiste_lanzaPersonaNoEncontradaYNoElimina() {
        when(personaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PersonaNoEncontradaException.class, () -> personaService.eliminar(99L));
        verify(personaRepository, never()).delete(any(Persona.class));
    }
}
