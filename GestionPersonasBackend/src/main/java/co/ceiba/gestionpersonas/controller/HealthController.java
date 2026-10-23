package co.ceiba.gestionpersonas.controller;

import co.ceiba.gestionpersonas.dto.HealthResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HealthResponse> health() {
    return ResponseEntity.ok(new HealthResponse("OK", "API funcionando"));
  }
}  