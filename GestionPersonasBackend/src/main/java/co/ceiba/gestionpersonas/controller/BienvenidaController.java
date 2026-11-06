package co.ceiba.gestionpersonas.controller;

import co.ceiba.gestionpersonas.dto.BienvenidaResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class BienvenidaController {

    @GetMapping(value = "/welcome/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BienvenidaResponse> bienvenida(@PathVariable("nombre") String nombre) {
        return ResponseEntity.ok(new BienvenidaResponse(
                "Hola, " + nombre + ". Te damos la bienvenida a la API de gestión de personas."));
    }
}
