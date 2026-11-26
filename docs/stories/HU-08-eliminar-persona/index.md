# HU-08: Eliminar persona

| | |
|---|---|
| Estado | ✅ Fusionada en `main` |
| Pull request | #34 |
| Rama | `feature/HU-08-eliminar-persona` |

Esta carpeta documenta la historia de eliminar una persona. El borrado es físico y provisional: en la HU-17 se cambia a borrado lógico.

- [historia.md](historia.md): la historia y sus criterios de aceptación.
- [refinamiento.md](refinamiento.md): lo que necesitaba antes, las dudas y las decisiones.

## Qué piezas se tocaron

| Pieza | Qué se hizo |
|---|---|
| Repositorio | No cambió: `findById` y `delete` ya vienen en `JpaRepository`. |
| Excepciones | Reutilicé `PersonaNoEncontradaException` (404). No hubo excepciones nuevas. |
| DTO | No se necesitó: el endpoint no recibe body ni devuelve datos. |
| Servicio | `eliminar` busca la persona con `findById` y la borra con `delete`, dentro de un `@Transactional`. Registra logs con SLF4J (info con el id eliminado y warn en el 404). |
| Controlador | Agregué `DELETE /api/personas/{id}`, que responde 204 sin contenido con `ResponseEntity.noContent()`. |
| `GlobalExceptionHandler` | No cambió: ya traducía la excepción a un 404 con el mismo formato de las otras historias. |
| Migraciones | No hubo migración de Flyway porque el esquema no cambió. |
| Test del servicio | Eliminación exitosa y persona inexistente. |

## Evidencia

Las capturas están en la descripción del PR #34:
- Creación de Elena (201), la persona que usé para las pruebas.
- Eliminación de una persona que existe (204 sin contenido).
- Petición con un id que no existe (404 con el mismo formato de error).
- Log de la terminal de la aplicación con `Persona eliminada con id N` y el `warn` del 404.
- Resultado de los tests y el build (13 tests: 12 del servicio y 1 de contexto).