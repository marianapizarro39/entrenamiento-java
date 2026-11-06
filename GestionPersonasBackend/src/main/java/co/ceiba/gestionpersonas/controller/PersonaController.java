package co.ceiba.gestionpersonas.controller;

import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;
import co.ceiba.gestionpersonas.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<PersonaResponse> crear(@Valid @RequestBody PersonaRequest request) {
        PersonaResponse creada = personaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
}