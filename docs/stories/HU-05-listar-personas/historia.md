# HU-05: Listar personas

**Puntos:** 3

**Como** administrador del sistema
**quiero** poder ver todas las personas registradas
**para** tener visibilidad del registro completo.

## Criterios de aceptación
- `GET /api/personas` retorna la lista de personas.
- Si no hay personas, retorna 200 OK con lista vacía `[]` (no 404).
- La respuesta usa DTOs, no entidades.
- Ordenado por apellido ascendente.
- El ordenamiento ocurre en la base de datos, no en memoria (activo `show-sql` y muestro el `ORDER BY` generado).
- El mapeo de entidad a DTO usa la API de Streams.
- Al menos un test unitario del servicio.

Los dos criterios del centro de la historia son el del `ORDER BY` en la base de datos y el del mapeo con Streams.
