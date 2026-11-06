package co.ceiba.gestionpersonas.repository;

import co.ceiba.gestionpersonas.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
    boolean existsByEmail(String email);
}
