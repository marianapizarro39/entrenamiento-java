package co.ceiba.gestionpersonas.service.impl;

import co.ceiba.gestionpersonas.domain.Persona;
import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;
import co.ceiba.gestionpersonas.exception.EmailYaExisteException;
import co.ceiba.gestionpersonas.exception.PersonaNoEncontradaException;
import co.ceiba.gestionpersonas.repository.PersonaRepository;
import co.ceiba.gestionpersonas.service.PersonaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        return aRespuesta(guardada);
    }

    @Override
    public List<PersonaResponse> listar() {
        return personaRepository.findAllByOrderByApellidoAsc().stream()
                .map(this::aRespuesta)
                .toList();
    }

    private PersonaResponse aRespuesta(Persona persona) {
        return new PersonaResponse(
                persona.getId(),
                persona.getNombre(),
                persona.getApellido(),
                persona.getEmail(),
                persona.getFechaNacimiento()
        );
    }

    @Override
    public PersonaResponse buscarPorId(Long id) {
        return personaRepository.findById(id)
                .map(this::aRespuesta)
                .orElseThrow(() -> new PersonaNoEncontradaException(id));
    }

    @Override
    @Transactional
    public PersonaResponse actualizar(Long id, PersonaRequest request) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new PersonaNoEncontradaException(id));

        String email = request.email().trim().toLowerCase(Locale.ROOT);

        // Solo hay conflicto si el email lo tiene OTRA persona
        if (personaRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailYaExisteException(email);
        }

        persona.setNombre(request.nombre().trim());
        persona.setApellido(request.apellido().trim());
        persona.setEmail(email);
        persona.setFechaNacimiento(request.fechaNacimiento());

        // Como el método es @Transactional, Hibernate guardaría estos cambios solo
        // al cerrar la transacción (dirty checking). Llamo a save() para que quede explícito.
        Persona actualizada = personaRepository.save(persona);

        return aRespuesta(actualizada);
    }
}
