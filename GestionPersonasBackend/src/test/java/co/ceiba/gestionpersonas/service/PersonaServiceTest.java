package co.ceiba.gestionpersonas.service;

import co.ceiba.gestionpersonas.domain.Persona;
import co.ceiba.gestionpersonas.service.impl.PersonaServiceImpl;
import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;
import co.ceiba.gestionpersonas.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
}