# HU-07: Actualizar Persona

**Puntos:** 5

## Historia de usuario
**Como** administrador del sistema
**quiero** poder modificar los datos de una persona existente
**para** mantener la información actualizada.

## Criterios de aceptación
- [ ] `PUT /api/personas/{id}` recibe un JSON con los datos actualizados.
- [ ] Aplica las mismas validaciones que la creación.
- [ ] Si la persona existe, actualiza y retorna `200 OK` con los datos nuevos.
- [ ] Si no existe, retorna `404 Not Found`.
- [ ] El id no puede modificarse (se ignora si viene en el body).
- [ ] El email debe seguir siendo único: retorna `409 Conflict` si choca.
- [ ] La operación es idempotente: dos PUT iguales dejan el mismo estado.
- [ ] Tests: actualización exitosa, persona inexistente y email duplicado.

## Detalle técnico a discutir
Dentro de un método `@Transactional`, modificar una entidad gestionada la persiste al cerrar la transacción sin llamar a `save()`. Eso es el dirty checking de Hibernate.