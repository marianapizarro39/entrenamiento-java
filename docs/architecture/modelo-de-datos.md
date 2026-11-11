# Modelo de datos

Una sola tabla por ahora, creada con las migraciones de Flyway en `src/main/resources/db/migration/`.

## Tabla `personas`

| Columna | Tipo | Reglas |
|---------|------|--------|
| `id` | `BIGSERIAL` | Llave primaria |
| `nombre` | `VARCHAR(100)` | Obligatorio |
| `apellido` | `VARCHAR(100)` | Obligatorio |
| `email` | `VARCHAR(150)` | Obligatorio y único (`uk_personas_email`) |
| `fecha_nacimiento` | `DATE` | Obligatoria desde la migración V2 |

El email se guarda en minúsculas y sin espacios, así que `Ana@x.com` y `ana@x.com` cuentan como la misma persona.

## Migraciones

- `V1__crear_tabla_personas.sql`: crea la tabla.
- `V2__fecha_nacimiento_obligatoria.sql`: hace obligatoria la fecha de nacimiento.
