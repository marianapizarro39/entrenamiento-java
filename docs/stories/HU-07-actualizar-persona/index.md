# HU-07: Actualizar persona

| | |
|---|---|
| Estado | ✅ Fusionada en `main` |
| Pull request | #33 |
| Rama | `feature/HU-07-actualizar-persona` |

Esta carpeta documenta la historia de actualizar los datos de una persona que ya existe.

- [historia.md](historia.md): la historia y sus criterios de aceptación.
- [refinamiento.md](refinamiento.md): lo que necesitaba antes, las dudas y las decisiones.

## Qué piezas se tocaron
| Pieza | Qué se hizo |
|---|---|
| Repositorio | Agregué `existsByEmailAndIdNot` para revisar si el email ya lo tiene otra persona. |
| Excepciones | Reutilicé `PersonaNoEncontradaException` (404) y `EmailYaExisteException` (409). No hubo excepciones nuevas. |
| DTO | Reutilicé `PersonaRequest`, que ya tiene las validaciones del registro. No se creó un DTO nuevo. |
| Servicio | `actualizar` busca la persona, valida el email, aplica los cambios y llama a `save()` de forma explícita. También registra logs con SLF4J (info al empezar y warn en el 404 y el 409). |
| Controlador | Agregué `PUT /api/personas/{id}`, que toma siempre el id del path. |
| `GlobalExceptionHandler` | No cambió: ya traducía las excepciones a 404, 409 y los datos inválidos a 400. |
| Test del servicio | Actualización exitosa, persona inexistente y email duplicado. |

## Evidencia
Las capturas están en la descripción del PR #33:
- Actualización de una persona que existe (200 OK con los datos nuevos).
- El mismo PUT dos veces seguidas, con el mismo resultado (idempotencia).
- Petición con un id que no existe (404 con mensaje claro).
- Petición con un email que ya tiene otra persona (409).
- Petición con un id distinto en el body, que se ignora (200 con el id del path).
- Peticiones con datos inválidos: nombre en blanco, email inválido e id que no es un número (400).
- Resultado de los tests y el build.