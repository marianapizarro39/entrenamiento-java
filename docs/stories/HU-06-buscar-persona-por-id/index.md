# HU-06: Buscar persona por ID

| | |
|---|---|
| Estado | En desarrollo |
| Pull request | Pendiente |
| Rama | `feature/HU-06-buscar-persona-por-id` |

Esta carpeta documenta la historia de buscar una persona por su id.

- [historia.md](historia.md): la historia y sus criterios de aceptación.
- [refinamiento.md](refinamiento.md): lo que necesitaba antes, las dudas y las decisiones.

## Qué piezas se van a tocar
| Pieza | Para qué sirve |
|---|---|
| Repositorio | Devuelve `Optional<Persona>` al buscar por id. |
| Excepción | `PersonaNoEncontradaException`, para cuando no existe la persona. |
| Servicio | Busca con `orElseThrow` y convierte la entidad a DTO. |
| Controlador | Expone `GET /api/personas/{id}`. |
| `@ControllerAdvice` | Traduce la excepción a 404 y el id inválido a 400. |
| Test del servicio | Comprueba el caso encontrado y el no encontrado. |

## Evidencia
Se agrega al terminar el código, con capturas de pantalla de:
- Petición con un id que existe (200 OK).
- Petición con un id que no existe (404 con mensaje claro).
- Petición con un id inválido, como `/api/personas/abc` (400).
- Resultado de los tests y el build.