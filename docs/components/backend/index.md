# Backend: reglas de cada paquete

Código en `GestionPersonasBackend/src/main/java/co/ceiba/gestionpersonas/`.

| Paquete | Qué va | Qué no va |
|---------|--------|-----------|
| `controller` | Recibe las peticiones HTTP, valida con `@Valid`, le pasa el trabajo al servicio y devuelve la respuesta | Lógica de negocio, acceso a datos, la entidad `Persona` |
| `service` | Las interfaces con la lógica de negocio | Nada de HTTP (códigos, `ResponseEntity`) |
| `service.impl` | La implementación de cada servicio | Consultas directas a la base de datos |
| `repository` | Guardar y consultar datos con Spring Data JPA, usando solo el `domain` | Lógica de negocio y llamadas al `service` |
| `domain` | Las clases del negocio, como la entidad `Persona` | Dependencias de los otros paquetes |
| `dto` | Los objetos que entran y salen de la API (`PersonaRequest`, `PersonaResponse`, `ErrorResponse`) | Lógica |
| `exception` | Las excepciones propias y el `GlobalExceptionHandler` que las convierte en respuestas HTTP | Lógica de negocio |

## Reglas generales

- Las dependencias van hacia abajo: `controller` → `service` → `repository`.
- Las dependencias se inyectan por constructor, nunca con `@Autowired` en el campo.
- El controlador nunca devuelve la entidad `Persona`, solo DTOs.
- Cada historia agrega a las interfaces solo los métodos que necesita.

## Contrato de la API

Los endpoints, con sus peticiones y respuestas, están en [api.md](api.md).
