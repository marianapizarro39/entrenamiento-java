# 👥 Gestión de personas

API REST en Java 21 y Spring Boot para administrar un registro de personas, con PostgreSQL como BD (la conexión a la base de datos llega en el Mes 2). Este proyecto es de mi etapa productiva en Ceiba Software.

## 📁 Estructura

| Carpeta | Qué contiene |
|---------|--------------|
| `docs/` | Documentación: arquitectura, componentes e historias |
| `GestionPersonasBackend/` | API REST en Java + Spring Boot |

## ▶️ Cómo ejecutarlo

Requisitos: JDK 21 y Git. PostgreSQL se necesitará desde la HU-04.

1. Crear una base de datos en PostgreSQL y definir las variables de entorno de conexión que usa el `application.yml` (la URL, el usuario y la contraseña). Los datos de conexión no se guardan en el repositorio.
2. Ejecutar:

```bash
cd GestionPersonasBackend
./gradlew bootRun
```

En Windows: `gradlew.bat bootRun`. La API queda en `http://localhost:8080`. Al arrancar, Flyway crea la tabla de personas automáticamente.

## 🔌 Endpoints disponibles

| Método | Ruta | Respuesta |
|--------|------|-----------|
| GET | `/api/health` | `{"status":"OK","message":"API funcionando"}` |
| GET | `/api/welcome/{nombre}` | `{"message":"Hola, {nombre}. Te damos la bienvenida a la API de gestión de personas."}` |
| POST | `/api/personas` | `201` con la persona creada y su `id`. `400` si los datos no son válidos y `409` si el email ya existe |

Ejemplo de petición para `POST /api/personas`:

```json
{
  "nombre": "Ana",
  "apellido": "Gómez",
  "email": "ana.gomez@correo.com",
  "fechaNacimiento": "1998-05-14"
}
```

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
