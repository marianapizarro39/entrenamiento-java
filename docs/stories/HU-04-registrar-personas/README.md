# HU-04: Registrar personas

Este PR depende del PR #[número] (HU-03). Su base es `feature/HU-03-estructura-base` y cambiará a `main` cuando se mergee la HU-03.

## Historia
COMO administrador del sistema
QUIERO poder registrar nuevas personas en el sistema
PARA mantener un registro actualizado de usuarios

## ¿Qué hace?

Implementa el registro de personas: el endpoint `POST /api/personas` recibe un DTO, valida los datos, verifica que el email no esté repetido y guarda la persona en PostgreSQL con Spring Data JPA. La tabla se crea con una migración de Flyway.

También activa `@Service` en `PersonaServiceImpl` y `@RestController` en `PersonaController`, que quedaron sin anotar en la HU-03 porque `PersonaRepository` todavía no tenía implementación.

## Por qué existe cada pieza
- `PersonaRequestDTO` / `PersonaResponseDTO`: el controller recibe y devuelve DTOs, nunca la entidad JPA.
- `Persona` (entidad JPA): ahora tiene las anotaciones para guardarse en la base de datos. Por eso en la HU-03 la dejé como clase y no como `record`.
- `PersonaRepository` (`JpaRepository`): acceso a datos, con `existsByEmail` para revisar si el email ya existe.
- `PersonaServiceImpl`: tiene la lógica de negocio (verifica que el email sea único y guarda).
- Migración `V1` (Flyway): crea la tabla con restricción `UNIQUE` en el email. Dejé `ddl-auto` en `validate` para que el esquema no dependa de Hibernate.
- Manejador global de excepciones (`@RestControllerAdvice`): convierte los errores de validación en 400 con mensajes claros y el email duplicado en 409.

## Criterios de aceptación
- [x] `POST /api/personas` recibe JSON con los datos de la persona
- [x] Validación: nombre y apellido obligatorios (no vacíos ni solo espacios)
- [x] Validación: email con formato válido
- [x] Validación: email único en el sistema
- [x] Validación: `fechaNacimiento` no puede ser futura
- [x] La persona se guarda en PostgreSQL con Spring Data JPA
- [x] El esquema se crea con una migración de Flyway versionada, no con `ddl-auto: update`
- [x] Retorna `201 Created` con la persona creada y su `id`
- [x] Retorna `400 Bad Request` con mensajes descriptivos si falla la validación
- [x] Retorna `409 Conflict` si el email ya existe
- [x] El controlador recibe y retorna DTOs, nunca la entidad JPA
- [x] Al menos un test unitario del servicio (camino feliz)

## Decisiones y dudas
- La unicidad del email se valida en el servicio (`existsByEmail`) y también con `UNIQUE` en la base de datos, por si dos peticiones llegan al mismo tiempo.
- La conexión a PostgreSQL se configura en `application.yml` con variables de entorno, así no hay secretos en el repositorio.
- Las interfaces que quedaron vacías en la HU-03 ahora tienen los métodos que pide esta historia, y solo esos.
- Duda: no sé si con un test del camino feliz alcanza o si conviene agregar uno para el caso del email repetido.

## Cómo probarlo
1. Levantar PostgreSQL y definir las variables de entorno de conexión.
2. Ejecutar `./gradlew build` dentro de `GestionPersonasBackend/`. Debe terminar en `BUILD SUCCESSFUL`, con los tests pasando.
3. Ejecutar la aplicación con `./gradlew bootRun` y probar con Postman, curl o `pruebas.http`:
   - `POST /api/personas` con datos válidos → `201` con el `id` generado
   - `POST` con nombre vacío, email inválido o fecha futura → `400` con mensajes
   - `POST` repitiendo un email → `409`
4. Probar `GET /api/health` y comprobar que sigue respondiendo `200`.

## DoD Nivel 1
- [x] Compila sin errores ni warnings nuevos
- [x] Criterios de aceptación probados a mano
- [x] Código en GitHub, en la rama `feature/HU-04-registro-personas`
- [x] Al menos 3 commits con mensajes descriptivos
- [x] Pull Request abierto con la plantilla del equipo
- [ ] Pull Request aprobado (pendiente de revisión)
- [x] README actualizado