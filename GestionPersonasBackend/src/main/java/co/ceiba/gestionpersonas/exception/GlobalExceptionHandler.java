package co.ceiba.gestionpersonas.exception;

import co.ceiba.gestionpersonas.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Los datos enviados no son válidos",
                errores
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }

    @ExceptionHandler(EmailYaExisteException.class)
    public ResponseEntity<ErrorResponse> manejarEmailDuplicado(EmailYaExisteException ex) {
        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }

    // por si dos peticiones con el mismo email llegan casi al mismo tiempo
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> manejarIntegridad(DataIntegrityViolationException ex) {
        ErrorResponse respuesta = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Ya existe una persona registrada con ese email",
                List.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
    }
}