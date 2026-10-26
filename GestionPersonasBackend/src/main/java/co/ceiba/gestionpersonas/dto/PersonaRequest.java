package co.ceiba.gestionpersonas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record PersonaRequest (

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank (message = "El apellido es obligatorio")
    String apellido,

    @NotBlank (message = "El correo es obligatorio")
    @Email (message = "El correo no esválido")
    String email,

    @PastOrPresent (message = "La fecha de nacimiento no puede ser futura")
    LocalDate fechaNacimiento
  
) {
}
