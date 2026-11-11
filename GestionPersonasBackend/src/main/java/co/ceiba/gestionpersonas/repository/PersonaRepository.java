package co.ceiba.gestionpersonas.repository;

import co.ceiba.gestionpersonas.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
    boolean existsByEmail(String email);

        List<Persona> findAllByOrderByApellidoAsc();
}
