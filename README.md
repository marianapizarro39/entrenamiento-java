# 👥 Gestión de personas 

API REST en Java 21 y Spring Boot para administrar un registro de personas, con PostgreSQL como BD. Este proyecto es de mi etapa productiva en Ceiba Software.

## 📁 Estructura 

| Carpeta | Qué contiene |
|---------|--------------|
| `docs/ `| Documentación: arquitectura, componentes e historias |
|`GestionPersonasBackend/`| API REST en Java + Spring Boot |

## ▶️ Cómo ejecutarlo

Requisitos: JDK 21, Maven y PostgreSQL


        cd GestionPersonasBackend
        mvn spring-boot:run

<<<<<<< Updated upstream
=======
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
  "nombre": "Mariana",
  "apellido": "Pizarro",
  "email": "mariana@correo.com",
  "fechaNacimiento": "1998-05-14"
}
```

Para probarlos: abre `GestionPersonasBackend/pruebas.http` con la extensión REST Client y pulsa **Send Request** sobre cada petición. También se puede probar con Postman o curl.

## 🧱 Estructura de paquetes

Dentro de `GestionPersonasBackend/src/main/java/com/ceiba/gestion_personas_backend/`:

| Paquete | Para qué existe |
|---------|-----------------|
| `controller` | Recibe las peticiones HTTP y delega al servicio |
| `service` | Lógica de negocio (interfaces) |
| `service.impl` | Implementación de los servicios |
| `repository` | Acceso a datos con Spring Data JPA |
| `domain` | Clases del negocio, como `Persona` |
| `dto` | Objetos con los que la API recibe y devuelve datos |

Las dependencias van hacia abajo (controller → service → repository) y se inyectan por constructor. El controlador nunca expone la entidad `Persona`, solo DTOs.
>>>>>>> Stashed changes

## 📃 Documentación

- [Arquitectura del sistema](docs/architecture/index.md)
- [Historias de usuario](docs/stories/index.md)