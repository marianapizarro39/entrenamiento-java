# HU-06: Buscar persona por ID

**Puntos:** 3

## La historia
**Como** administrador del sistema
**quiero** poder buscar una persona específica por su identificador
**para** ver los detalles completos de un registro.

## Criterios de aceptación
- `GET /api/personas/{id}` retorna la persona solicitada.
- Si existe, retorna 200 OK con sus datos.
- Si no existe, retorna 404 Not Found con un mensaje claro.
- Si el id tiene formato inválido, retorna 400 Bad Request.
- El repositorio devuelve `Optional<Persona>`.
- El servicio lanza `PersonaNoEncontradaException` usando `orElseThrow`. Nunca devuelve `null` ni llama a `.get()` sin verificar.
- Un `@ControllerAdvice` global traduce la excepción a la respuesta HTTP.
- Tests: caso encontrado y caso no encontrado.

## Para discutir en la sesión
¿Por qué `Optional` y no `null`? Porque `null` no aparece en la firma del método: quien lo llama no tiene forma de saber que debe comprobarlo, y el compilador no le avisa. `Optional<Persona>` lo dice en el tipo.