package co.ceiba.gestionpersonas.service;

import co.ceiba.gestionpersonas.dto.PersonaRequest;
import co.ceiba.gestionpersonas.dto.PersonaResponse;

public interface PersonaService {

    PersonaResponse crear(PersonaRequest request);
}