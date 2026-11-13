# API

Base: `http://localhost:8080`. Todas las respuestas son JSON.

## GET /api/health

Comprueba que la API está viva.

**200**

```json
{ "status": "OK", "message": "API funcionando" }
```

## GET /api/welcome/{nombre}

Devuelve un saludo personalizado.

**200**

```json
{ "message": "Hola, Ana. Te damos la bienvenida a la API de gestión de personas." }
```

## POST /api/personas

Registra una persona.

### Petición

```json
{
  "nombre": "Ana",
  "apellido": "Gómez",
  "email": "ana.gomez@correo.com",
  "fechaNacimiento": "1998-05-14"
}
```

| Campo | Tipo | Reglas |
|-------|------|--------|
| `nombre` | texto | Obligatorio, no puede estar vacío ni tener solo espacios. Máximo 100 caracteres |
| `apellido` | texto | Obligatorio, no puede estar vacío ni tener solo espacios. Máximo 100 caracteres |
| `email` | texto | Obligatorio, con formato válido y único en el sistema. Máximo 150 caracteres |
| `fechaNacimiento` | fecha `AAAA-MM-DD` | Obligatoria, no puede ser futura |

Antes de guardar se quitan los espacios de los extremos. El email se guarda en minúsculas, así que `Ana@x.com` y `ana@x.com` son la misma persona.

### Respuestas

**201 Created**: la persona creada, con su `id`.

```json
{
  "id": 1,
  "nombre": "Ana",
  "apellido": "Gómez",
  "email": "ana.gomez@correo.com",
  "fechaNacimiento": "1998-05-14"
}
```

**400 Bad Request**: algún dato no es válido. `errores` trae un mensaje por cada campo, con el formato `campo: mensaje`.

```json
{
  "status": 400,
  "mensaje": "Los datos enviados no son válidos",
  "errores": [
    "nombre: El nombre es obligatorio",
    "fechaNacimiento: La fecha de nacimiento no puede ser futura"
  ]
}
```

Mensajes posibles:

| Campo | Mensaje |
|-------|---------|
| `nombre` | El nombre es obligatorio / El nombre no puede tener más de 100 caracteres|
| `apellido` | El apellido es obligatorio / El apellido no puede tener más de 100 caracteres |
| `email` | El correo es obligatorio / El correo no es válido / El correo no puede tener más de 150 caracteres |
| `fechaNacimiento` | La fecha de nacimiento es obligatoria / La fecha de nacimiento no puede ser futura |

**409 Conflict**: el email ya está registrado.

```json
{
  "status": 409,
  "mensaje": "Ya existe una persona registrada con el correo ana.gomez@correo.com",
  "errores": []
}
```

**409 Conflict** (genérico): la base de datos rechazó el registro por una restricción de datos duplicados, por ejemplo cuando dos peticiones con el mismo email llegan casi al mismo tiempo.

```json
{
  "status": 409,
  "mensaje": "Ya existe un registro con esos datos",
  "errores": []
}
```

## GET /api/personas

Lista todas las personas registradas, ordenadas por apellido.

**200**: la lista de personas. Cada elemento tiene los mismos campos que la respuesta de `POST /api/personas`.

```json
[
  {
    "id": 2,
    "nombre": "Santiago",
    "apellido": "Cano",
    "email": "cano@correo.com",
    "fechaNacimiento": "2006-08-18"
  },
  {
    "id": 1,
    "nombre": "Ana",
    "apellido": "Gómez",
    "email": "ana.gomez@correo.com",
    "fechaNacimiento": "1998-05-14"
  }
]
```

Si no hay personas registradas, la respuesta sigue siendo **200** con una lista vacía. No es un error.

```json
[]
```
