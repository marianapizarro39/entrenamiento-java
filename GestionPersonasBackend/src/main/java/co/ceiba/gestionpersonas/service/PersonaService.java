package co.ceiba.gestionpersonas.service;

import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;

import java.util.List;

public interface PersonaService {

    PersonaResponse crear(PersonaRequest request);

        List<PersonaResponse> listar();
}
