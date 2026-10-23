package co.ceiba.gestionpersonas.controller;

import co.ceiba.gestionpersonas.service.PersonaService;

public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Implementación de los métodos del controlador
}