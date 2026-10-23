# 🧭 Decisiones del proyecto

Aquí dejo las decisiones generales que he tomado en el proyecto de gestión de personas y por qué. Las voy a ir actualizando a medida que avance. Las decisiones propias de cada historia están en `docs/stories/`.

## 1. Stack tecnológico

**Decisión:** Java 21, Spring Boot y Gradle.

**Por qué:** Java 21 es una versión LTS, o sea que tiene soporte a largo plazo, y es la que pide el plan de entrenamiento como mínima. Spring Boot me ahorra mucha configuración y es lo que se usa en el equipo. Para Gradle: <escribe aquí tu razón real, por ejemplo por qué lo elegiste en vez de Maven. El plan dice que Maven es lo recomendado pero Gradle también es válido>.

## 2. Arquitectura en capas

**Decisión:** organicé el código en capas: `controller`, `service`, `repository`, `domain` y `dto`.

**Por qué:** cada capa tiene una sola responsabilidad y así no se mezcla todo en una misma clase. El controller solo recibe la petición y le pasa el trabajo al servicio, el servicio tiene la lógica de negocio y el repositorio es el único que sabe cómo se guardan los datos.

| Capa | Paquete | Qué hace |
|------|---------|----------|
| Presentación | `controller` | Recibe las peticiones HTTP y devuelve la respuesta |
| Negocio | `service` y `service.impl` | Lógica de negocio |
| Datos | `repository` | Guarda y consulta los datos |
| Dominio | `domain` | Clases del negocio, como `Persona` |
| Contrato de la API | `dto` | Objetos que entran y salen por la API |

**Regla que sigo:** las dependencias van hacia abajo (controller → service → repository) y nunca hacia arriba. Si un repositorio necesitara saber algo del controller, sería señal de que algo está en el lugar equivocado.

**Alternativa que no usé:** meter todo en pocas clases. Es más rápido al principio, pero después es muy difícil de cambiar y de probar.

## 3. Interfaces para el servicio y el repositorio

**Decisión:** `PersonaService` y `PersonaRepository` son interfaces, y la lógica va en una clase aparte (`PersonaServiceImpl`).

**Por qué:** así cada capa depende de un contrato y no de una clase concreta. Si más adelante cambia cómo se guardan los datos, no tengo que tocar el servicio ni el controller. Además, el plan dice que las interfaces son la base para entender la arquitectura hexagonal del Mes 5.

## 4. Inyección de dependencias por constructor

**Decisión:** todas las dependencias entran por el constructor, con campos `final`. No uso `@Autowired` en los campos.

**Por qué:**
- Se ve claro qué necesita cada clase para funcionar.
- Puedo usar `final`, así la dependencia no cambia después de crear el objeto.
- Puedo crear la clase en un test sin levantar Spring.

## 5. Records para los DTOs

**Decisión:** las respuestas de la API son `record` (por ejemplo `HealthResponse` y `BienvenidaResponse`) y no `Map`.

**Por qué:** un `Map` no tiene un contrato claro. Si escribo mal el nombre de una llave, compila igual y el error solo aparece cuando alguien prueba el endpoint. Con un `record` los campos están definidos, un error de escritura no compila y el orden del JSON es el mismo en que declaro los campos. Además son inmutables y se escriben en pocas líneas.

## 6. `Persona` es una clase y no un record

**Decisión:** `Persona` es una clase normal.

**Por qué:** más adelante va a ser una entidad de JPA, y JPA necesita una clase que pueda modificar. Los DTOs sí son records porque solo transportan datos.

## 7. Un controller por responsabilidad

**Decisión:** `HealthController` (¿está viva la API?) y `BienvenidaController` (el saludo) están separados.

**Por qué:** uno es operativo y el otro es funcional. Separarlos hace que cada clase tenga un solo motivo para cambiar. Antes los tenía juntos en `InicioController`, pero ese nombre no describía ninguno de los dos.

## 8. Nombres de paquetes y de clases

**Decisión:** los paquetes van en minúsculas y sin guiones bajos (`co.ceiba.gestionpersonas`). Las clases van en PascalCase y los métodos en camelCase.

**Por qué:** es lo que dice la Google Java Style Guide. Spring Initializr había generado el paquete con guiones bajos, y preferí corregirlo temprano porque cada archivo nuevo hace el cambio más costoso.

## 9. Forma de trabajar

**Decisión:** una rama por historia (`feature/HU-XX-descripcion`), commits descriptivos y Pull Request con la plantilla del equipo.

**Por qué:** así cada historia se revisa por separado y queda escrito por qué se hizo cada cosa. Antes de pedir revisión marco la Definition of Done ítem por ítem en el PR.

## 10. Cómo pruebo la API

**Decisión:** uso un archivo `pruebas.http` dentro de `GestionPersonasBackend/`, con la variable `@baseUrl`.

**Por qué:** queda guardado en el repositorio y cualquiera puede probar los mismos endpoints. Con la variable no repito la URL en cada petición.

## ⏳ Decisiones pendientes

Estas todavía no las he tomado porque no llegan hasta las próximas historias:

- **Base de datos:** PostgreSQL con Spring Data JPA y migraciones con Flyway (Mes 2).
- **Validaciones:** cómo validar los datos de entrada, por ejemplo que el nombre no esté vacío (HU-04).
- **Manejo de errores:** cómo responder cuando una persona no existe (HU-05 en adelante).
- **Tests:** empiezo a escribirlos desde el Mes 2.