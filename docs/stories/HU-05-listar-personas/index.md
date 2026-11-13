# HU-05: Listar personas

| | |
|---|---|
| Estado | En desarrollo |
| Pull request | #31 |
| Rama | `feature/hu-05-listar-personas` |

Esta carpeta documenta la historia de listar personas.

- [historia.md](historia.md): la historia y sus criterios de aceptación.
- [refinamiento.md](refinamiento.md): lo que necesitaba antes, las dudas y las decisiones.

## Qué piezas se van a tocar
| Pieza | Para qué sirve |
|---|---|
| Repositorio | Trae las personas ya ordenadas por apellido desde la base de datos. |
| Servicio | Convierte las entidades a DTO con Streams. |
| Controlador | Expone `GET /api/personas`. |
| DTO de respuesta | Es lo que se devuelve, para no exponer la entidad. |
| Test del servicio | Comprueba la lista con datos y la lista vacía. |

## Evidencia
Se agrega al terminar el código, con capturas de pantalla de:
- SQL generado con el `ORDER BY` en la consola.
- Peticiones de `pruebas.http` (con personas y con lista vacía).
- Resultado de los tests y el build.