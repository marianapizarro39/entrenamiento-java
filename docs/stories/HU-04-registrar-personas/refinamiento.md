# Refinamiento de la HU-04

Historia: [historia.md](historia.md) · Estado: [index.md](index.md)

## Por qué existe cada pieza

- `PersonaRequest` / `PersonaResponse`: el controller recibe y devuelve DTOs, nunca la entidad JPA.
- `Persona` (entidad JPA): ahora tiene las anotaciones para guardarse en la base de datos. Por eso en la HU-03 la dejé como clase y no como `record`.
- `PersonaRepository` (`JpaRepository`): acceso a datos, con `existsByEmail` para revisar si el email ya existe.
- `PersonaServiceImpl`: tiene la lógica de negocio (verifica que el email sea único y guarda).
- Migración `V1` (Flyway): crea la tabla con restricción `UNIQUE` en el email. Dejé `ddl-auto` en `validate` para que el esquema no dependa de Hibernate.
- Manejador global de excepciones (`@RestControllerAdvice`): convierte los errores de validación en 400 con mensajes claros y el email duplicado en 409.

## Decisiones y dudas

- La unicidad del email se valida en el servicio (`existsByEmail`) y también con `UNIQUE` en la base de datos, por si dos peticiones llegan al mismo tiempo.
- La conexión a PostgreSQL se configura en `application.yml` con variables de entorno, así no hay secretos en el repositorio.
- Las interfaces que quedaron vacías en la HU-03 ahora tienen los métodos que pide esta historia, y solo esos.
- Duda: no sabía si con un test del camino feliz alcanzaba. La respuesta fue que no, y ya agregué el test del email repetido.

## Decisiones que tomé después de la revisión

- **Email sin distinguir mayúsculas:** se guarda en minúsculas y sin espacios, así `Ana@x.com` y `ana@x.com` son la misma persona y no se registran dos veces.
- **`fechaNacimiento` obligatoria:** hay que enviarla siempre. Agregué `@NotNull` en el DTO y la migración `V2` la hace `NOT NULL` en la tabla. Revisé antes que mi base de desarrollo no tuviera personas sin fecha.
- **Mensaje del 409 genérico:** el manejador de `DataIntegrityViolationException` ahora responde "Ya existe un registro con esos datos". Antes decía que el email ya existía ante cualquier violación de integridad, y si fallaba otra restricción el mensaje mentía. El mensaje específico del email se queda para `EmailYaExisteException`.
