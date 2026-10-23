package co.ceiba.gestionpersonas.service.impl;

import co.ceiba.gestionpersonas.repository.PersonaRepository;
import co.ceiba.gestionpersonas.service.PersonaService;

public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    // Implementación de los métodos de la interfaz PersonaService
}