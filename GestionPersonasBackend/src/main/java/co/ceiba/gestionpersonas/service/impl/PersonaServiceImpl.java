package co.ceiba.gestionpersonas.service.impl;

import co.ceiba.gestionpersonas.domain.Persona;
import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;
import co.ceiba.gestionpersonas.exception.EmailYaExisteException;
import co.ceiba.gestionpersonas.repository.PersonaRepository;
import co.ceiba.gestionpersonas.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaResponse crear(PersonaRequest request) {
        String email = request.email().trim().toLowerCase(Locale.ROOT);

        if (personaRepository.existsByEmail(email)) {
            throw new EmailYaExisteException(email);
        }

        Persona persona = new Persona(
                null,
                request.nombre().trim(),
                request.apellido().trim(),
                email,
                request.fechaNacimiento()
        );

        Persona guardada = personaRepository.save(persona);

        return aRESPUESTA(guardada);
    }

    @Override
    public List <PersonaResponse> listar() {
        return personaRepository.findAllByOrderByApellidoAsc().stream()
                .map(this::aRESPUESTA)
                .toList();
    }

    private PersonaResponse aRESPUESTA(Persona persona) {
        return new PersonaResponse(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getEmail(),
                persona.getFechaNacimiento()
        );
    }
}
