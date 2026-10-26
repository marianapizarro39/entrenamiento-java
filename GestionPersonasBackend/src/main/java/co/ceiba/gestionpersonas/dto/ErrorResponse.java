package co.ceiba.gestionpersonas.dto;

import java.util.List;

public record ErrorResponse (
    int status,
    String mensaje,
    List<String> errores
){ 
}
