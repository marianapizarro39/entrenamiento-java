package co.ceiba.gestionpersonas.repository;

import co.ceiba.gestionpersonas.domain.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    Persona guardar(Persona persona);
    List<Persona> listar();
    Optional<Persona> buscarPorId(Long id);
}