package co.ceiba.gestionpersonas.service.impl;

import co.ceiba.gestionpersonas.domain.Persona;
import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;
import co.ceiba.gestionpersonas.exception.EmailYaExisteException;
import co.ceiba.gestionpersonas.repository.PersonaRepository;
import co.ceiba.gestionpersonas.service.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaResponse crear(PersonaRequest request) {
        String email = request.email().trim();

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

        return new PersonaResponse(
                guardada.getId(),
                guardada.getNombre(),
                guardada.getApellido(),
                guardada.getEmail(),
                guardada.getFechaNacimiento()
        );
    }
}