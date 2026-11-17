package co.ceiba.gestionpersonas.exception;

public class PersonaNoEncontradaException extends RuntimeException {

    public PersonaNoEncontradaException(Long id) {
        super("No existe una persona con el id " + id);
    }
}
