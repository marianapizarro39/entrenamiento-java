package co.ceiba.gestionpersonas.dto;

import java.time.LocalDate;

public record PersonaDTO(
    Long id,
    String nombre, 
    String apellido, 
    String email,
    LocalDate fechaNacimiento) {}
