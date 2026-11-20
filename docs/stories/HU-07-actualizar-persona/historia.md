# HU-07: Actualizar Persona

**Puntos:** 5

## Historia de usuario
**Como** administrador del sistema
**quiero** poder modificar los datos de una persona existente
**para** mantener la información actualizada.

## Criterios de aceptación
- [x] `PUT /api/personas/{id}` recibe un JSON con los datos actualizados.
- [x] Aplica las mismas validaciones que la creación.
- [x] Si la persona existe, actualiza y retorna `200 OK` con los datos nuevos.
- [x] Si no existe, retorna `404 Not Found`.
- [x] El id no puede modificarse (se ignora si viene en el body).
- [x] El email debe seguir siendo único: retorna `409 Conflict` si choca.
- [x] La operación es idempotente: dos PUT iguales dejan el mismo estado.
- [x] Tests: actualización exitosa, persona inexistente y email duplicado.

## Detalle técnico a discutir
Dentro de un método `@Transactional`, modificar una entidad gestionada la persiste al cerrar la transacción sin llamar a `save()`. Eso es el dirty checking de Hibernate.