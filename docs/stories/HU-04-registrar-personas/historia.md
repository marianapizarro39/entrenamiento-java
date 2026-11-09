# HU-04: Registrar personas

## Historia

COMO administrador del sistema

QUIERO poder registrar nuevas personas en el sistema

PARA mantener un registro actualizado de usuarios

## ¿Qué hace?

Implementa el registro de personas: el endpoint `POST /api/personas` recibe un DTO, valida los datos, verifica que el email no esté repetido y guarda la persona en PostgreSQL con Spring Data JPA. La tabla se crea con una migración de Flyway.

También activa `@Service` en `PersonaServiceImpl` y `@RestController` en `PersonaController`, que quedaron sin anotar en la HU-03 porque `PersonaRepository` todavía no tenía implementación.

## Criterios de aceptación

- `POST /api/personas` recibe JSON con los datos de la persona
- Validación: nombre y apellido obligatorios (no vacíos ni solo espacios)
- Validación: email con formato válido
- Validación: email único en el sistema
- Validación: `fechaNacimiento` no puede ser futura
- La persona se guarda en PostgreSQL con Spring Data JPA
- El esquema se crea con una migración de Flyway versionada, no con `ddl-auto: update`
- Retorna `201 Created` con la persona creada y su `id`
- Retorna `400 Bad Request` con mensajes descriptivos si falla la validación
- Retorna `409 Conflict` si el email ya existe
- El controlador recibe y retorna DTOs, nunca la entidad JPA
- Al menos un test unitario del servicio

El contrato completo del endpoint está en [api.md](../../components/backend/api.md).
