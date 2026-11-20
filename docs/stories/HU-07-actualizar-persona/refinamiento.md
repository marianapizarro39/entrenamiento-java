# Refinamiento HU-07: Actualizar Persona

**Estado:** ✅ Fusionada en `main` (PR #33)

## Qué vamos a hacer
Agregar el endpoint `PUT /api/personas/{id}` para actualizar una persona que ya existe, siguiendo las capas que ya tenemos (controller, service, repository, domain, dto).

## Decisiones
- **DTO:** reutilizo `PersonaRequest`, que es un `record` y ya trae las mismas validaciones del registro. No uso `Map`.
- **El id:** siempre se toma del path. Si viene en el body se ignora, así nadie lo puede cambiar. Como `PersonaRequest` no tiene campo `id`, no hay que hacer nada más para ignorarlo.
- **Persona inexistente:** el service lanza `PersonaNoEncontradaException` y el controller responde 404.
- **Email único:** al actualizar solo es conflicto si el email ya lo tiene OTRA persona. Si es el de la misma persona no pasa nada, porque si no el PUT repetido fallaría y dejaría de ser idempotente. Para eso agregué `existsByEmailAndIdNot` en el repositorio. Si choca, lanza `EmailYaExisteException` y responde 409.
- **Email normalizado:** igual que al crear, le quito los espacios de los extremos y lo paso a minúsculas antes de compararlo y guardarlo.
- **Dirty checking:** el método del service es `@Transactional`, así que Hibernate guardaría los cambios solo al cerrar la transacción. Prefiero llamar a `save()` de forma explícita y dejar un comentario, para que se entienda qué está pasando y no parezca magia.
- **Logs:** uso un logger de SLF4J en `PersonaServiceImpl`. `info` al empezar la actualización y `warn` en el 404 y el 409, siempre con el id y nunca con el email, porque es un dato personal.
- **Base de datos:** no cambia el esquema, así que no hace falta migración Flyway.

## Respuestas esperadas
| Caso | Código |
|---|---|
| Actualiza bien | 200 |
| Datos inválidos | 400 |
| Id que no es un número | 400 |
| Persona no existe | 404 |
| Email de otra persona | 409 |

## Tests
1. Actualización exitosa (comprueba también que el email se guarde sin espacios y en minúsculas, y que se llame a `save()`).
2. Persona inexistente.
3. Email duplicado.

## Dudas
Hasta el momento no hay ninguna.

## Riesgos
- ✅ Confundir el chequeo de email único con el de la misma persona. **Resultado:** el PUT repetido con los mismos datos respondió 200 las dos veces, sin 409, porque `existsByEmailAndIdNot` no cuenta a la misma persona (captura del PUT dos veces).
- ✅ Que el id del body termine pisando el del path. **Resultado:** probé con `"id": 555` en el body y respondió 200 con el id del path, porque `PersonaRequest` no tiene ese campo.