package co.ceiba.gestion_personas_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@RestController
@RequestMapping("/api") 
public class InicioController {
    
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK", "message", "API funcionando");
    }

    @GetMapping ("/welcome/{nombre}")
    public Map<String, String> welcome(@PathVariable String nombre) {
        return Map.of("message", "Hola, " + nombre + ". Bienvenido a la API de gestión de personas.");
    }
}
