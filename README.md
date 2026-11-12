# 👥 Gestión de personas

API REST en Java 21 y Spring Boot para administrar un registro de personas, con PostgreSQL como BD. Este proyecto es de mi etapa productiva en Ceiba Software.

## 📁 Estructura

| Carpeta | Qué contiene |
|---------|--------------|
| `docs/` | Documentación: arquitectura, componentes e historias |
| `GestionPersonasBackend/` | API REST en Java + Spring Boot |

## ▶️ Cómo ejecutarlo

Requisitos: JDK 21, Git y PostgreSQL.

Los comandos de esta sección son para Git Bash.

1. **Variables de entorno.** Crear una base de datos en PostgreSQL. Copiar `GestionPersonasBackend/.env.example` a `GestionPersonasBackend/.env` y cargarlo antes de ejecutar:

```bash
cd GestionPersonasBackend
set -a; source .env; set +a
```

El ejemplo apunta a la base de prueba `gestion_personas_test` con el usuario `usuario_prueba`. Cambiar `tu_contraseña` por la contraseña de ese usuario en tu PostgreSQL local. El archivo `.env` no se sube al repositorio.

2. Ejecutar:

```bash
./gradlew bootRun
```

La API queda en `http://localhost:8080`.

## 🔌 Endpoints disponibles

| Método | Ruta | Respuesta |
|--------|------|-----------|
| GET | `/api/health` | `{"status":"OK","message":"API funcionando"}` |
| GET | `/api/welcome/{nombre}` | `{"message":"Hola, {nombre}. Te damos la bienvenida a la API de gestión de personas."}` |
| POST | `/api/personas` | `201` con la persona creada y su `id`. `400` si los datos no son válidos y `409` si el email ya existe |
| GET | `/api/personas` | `200` con la lista de personas ordenada por apellido ascendente. Si no hay ninguna, `200` con `[]`|

Ejemplo de petición para `POST /api/personas`:

```json
{
  "nombre": "Ana",
  "apellido": "Gómez",
  "email": "ana.gomez@correo.com",
  "fechaNacimiento": "1998-05-14"
}
```

Ejemplo de respuesta para `GET /api/personas` (ordenada por apellido):

```json
[
  {
    "id": 7,
    "nombre": "Sofía",
    "apellido": "Arias",
    "email": "sofia.arias@correo.com",
    "fechaNacimiento": "1999-12-05"
  },
  {
    "id": 5,
    "nombre": "Carla",
    "apellido": "Castro",
    "email": "carla.castro@correo.com",
    "fechaNacimiento": "1995-07-02"
  }
]
```
El orden por apellido se resuelve en la base de datos (`findAllByOrderByApellidoAsc`), no en memoria. Se puede ver activando `show-sql` de Hibernate.


Para probarlos: abre `GestionPersonasBackend/pruebas.http` con la extensión REST Client y pulsa **Send Request** sobre cada petición. También se puede probar con Postman o curl.

## 🧱 Estructura de paquetes

Dentro de `GestionPersonasBackend/src/main/java/co/ceiba/gestionpersonas/`:

| Paquete | Para qué existe |
|---------|-----------------|
| `controller` | Recibe las peticiones HTTP y delega al servicio |
| `service` | Lógica de negocio (interfaces) |
| `service.impl` | Implementación de los servicios |
| `repository` | Acceso a datos con Spring Data JPA |
| `domain` | Clases del negocio, como `Persona` |
| `dto` | Objetos con los que la API recibe y devuelve datos |
| `exception` | Excepciones propias y el manejador global que las convierte en respuestas HTTP |

Las dependencias van hacia abajo (controller → service → repository) y se inyectan por constructor. El controlador nunca expone la entidad `Persona`, solo DTOs.

## 📃 Documentación

- [Arquitectura del sistema](docs/architecture/index.md)
- [Historias de usuario](docs/stories/index.md)

## 🔀 Registro de Pull Requests

| HU | Título | PR | Estado | Sprint |
|----|--------|----|--------|--------|
| HU-01 | Configuración del entorno | — | ✅ | 1 |
| HU-02 | Primer endpoint REST | #23 | ✅ | 1 |
| HU-03 | Estructura base del proyecto | #25 | ✅ | 1 |
| HU-04 | Registro de personas | #29 | ✅ | 2 |
| HU-05 | Listar personas | #31 | 🔁 | 2 |

