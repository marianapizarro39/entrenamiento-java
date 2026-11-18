# HU-07: Actualizar persona

| | |
|---|---|
| Estado | 🚧 En desarrollo |
| Pull request | #33 |
| Rama | `feature/HU-07-actualizar-persona` |

Esta carpeta documenta la historia de actualizar los datos de una persona que ya existe.

- [historia.md](historia.md): la historia y sus criterios de aceptación.
- [refinamiento.md](refinamiento.md): lo que necesitaba antes, las dudas y las decisiones.

## Qué piezas se van a tocar
| Pieza | Para qué sirve |
|---|---|
| Repositorio | Busca la persona por id y revisa si el email ya lo tiene otra persona. |
| Excepciones | Reutiliza `PersonaNoEncontradaException` (404) y la del email duplicado (409). |
| DTO | Un `record` para el request de actualización, con las mismas validaciones del registro. |
| Servicio | Busca la persona, valida el email, aplica los cambios y llama a `save()` de forma explícita. |
| Controlador | Expone `PUT /api/personas/{id}` y toma siempre el id del path. |
| `@ControllerAdvice` | Traduce las excepciones a 404, 409 y los datos inválidos a 400. |
| Test del servicio | Comprueba la actualización exitosa, la persona inexistente y el email duplicado. |

## Evidencia
Se agrega al terminar el código, con capturas de pantalla de:
- Actualización de una persona que existe (200 OK con los datos nuevos).
- Petición con un id que no existe (404 con mensaje claro).
- Petición con un email que ya tiene otra persona (409).
- Petición con datos inválidos (400).
- Dos PUT iguales seguidos, para mostrar que es idempotente.
- Resultado de los tests y el build.