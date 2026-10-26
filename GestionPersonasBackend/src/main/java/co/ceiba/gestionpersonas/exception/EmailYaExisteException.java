package co.ceiba.gestionpersonas.exception;

public class EmailYaExisteException extends RuntimeException {
    
    public EmailYaExisteException(String email) {
        super("Ya existe una persona registrada con el correo" + email);
    }
}
