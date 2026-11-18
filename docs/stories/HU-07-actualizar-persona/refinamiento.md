# Refinamiento HU-07: Actualizar Persona

## Qué vamos a hacer
Agregar el endpoint `PUT /api/personas/{id}` para actualizar una persona que ya existe, siguiendo las capas que ya tenemos (controller, service, repository, domain, dto).

## Decisiones
- **DTO:** uso un `record` para el request, con las mismas validaciones del registro. No uso `Map`.
- **El id:** siempre se toma del path. Si viene en el body se ignora, así nadie lo puede cambiar.
- **Persona inexistente:** el service lanza la excepción de "no encontrada" y el controller responde 404.
- **Email único:** al actualizar solo es conflicto si el email ya lo tiene OTRA persona. Si es el de la misma persona no pasa nada, porque si no el PUT repetido fallaría y dejaría de ser idempotente. Si choca responde 409.
- **Dirty checking:** el método del service es `@Transactional`, así que Hibernate guardaría los cambios solo al cerrar la transacción. Prefiero llamar a `save()` de forma explícita y dejar un comentario, para que se entienda qué está pasando y no parezca magia.
- **Base de datos:** no cambia el esquema, así que no hace falta migración Flyway.

## Respuestas esperadas
| Caso | Código |
|---|---|
| Actualiza bien | 200 |
| Datos inválidos | 400 |
| Persona no existe | 404 |
| Email de otra persona | 409 |

## Tests
1. Actualización exitosa.
2. Persona inexistente.
3. Email duplicado.

## Riesgos
- Confundir el chequeo de email único con el de la misma persona.
- Que el id del body termine pisando el del path.