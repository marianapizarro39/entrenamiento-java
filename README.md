# 👥 Gestión de personas

API REST en Java 21 y Spring Boot para administrar un registro de personas, con PostgreSQL como BD (la conexión a la base de datos llega en el Mes 2). Este proyecto es de mi etapa productiva en Ceiba Software.

## 📁 Estructura

| Carpeta | Qué contiene |
|---------|--------------|
| `docs/` | Documentación: arquitectura, componentes e historias |
| `GestionPersonasBackend/` | API REST en Java + Spring Boot |

## ▶️ Cómo ejecutarlo

Requisitos: JDK 21 y Git. PostgreSQL se necesitará desde la HU-04.

```bash
cd GestionPersonasBackend
./gradlew bootRun
```

En Windows: `gradlew.bat bootRun`. La API queda en `http://localhost:8080`.

## 🔌 Endpoints disponibles

| Método | Ruta | Respuesta |
|--------|------|-----------|
| GET | `/api/health` | `{"status":"OK","message":"API funcionando"}` |
| GET | `/api/welcome/{nombre}` | `{"message":"Hola, {nombre}. Te damos la bienvenida a la API de gestión de personas."}` |

Para probarlos: abre `GestionPersonasBackend/pruebas.http` con la extensión REST Client y pulsa **Send Request** sobre cada petición.

## 🧱 Estructura de paquetes

Dentro de `GestionPersonasBackend/src/main/java/co/ceiba/gestionpersonas/`:

| Paquete | Para qué existe |
|---------|-----------------|
| `controller` | Recibe las peticiones HTTP y delega al servicio |
| `service` | Lógica de negocio (interfaces) |
| `service.impl` | Implementación de los servicios |
| `repository` | Acceso a datos |
| `domain` | Clases del negocio, como `Persona` |
| `dto` | Objetos con los que la API recibe y devuelve datos |

Las dependencias van hacia abajo (controller → service → repository) y se inyectan por constructor.

## 📃 Documentación

- [Arquitectura del sistema](docs/architecture/index.md)
- [Historias de usuario](docs/stories/index.md)

## 🔀 Registro de Pull Requests

| HU | Título | PR | Estado | Sprint |
|----|--------|----|--------|--------|
| HU-01 | Configuración del entorno | — | ✅ | 1 |
| HU-02 | Primer endpoint REST | #23 | 🔄 En revisión | 1 |
| HU-03 | Estructura base del proyecto | #25 | 🔄 En revisión | 1 |
