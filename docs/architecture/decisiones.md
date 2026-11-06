# 🧭 Decisiones del proyecto

Aquí dejo las decisiones generales que he tomado en el proyecto de gestión de personas y por qué. Las voy a ir actualizando a medida que avance. Las decisiones propias de cada historia están en `docs/stories/`.

## 1. Stack tecnológico

**Decisión:** Java 21, Spring Boot y Gradle. Desde la HU-04 también uso PostgreSQL, Spring Data JPA y Flyway.

**Por qué:** Java 21 es una versión LTS, o sea que tiene soporte a largo plazo, y es la que pide el plan de entrenamiento como mínima. Spring Boot me ahorra mucha configuración y es lo que se usa en el equipo. Empecé el proyecto con Maven, pero mi mentor me pidió migrarlo a Gradle, así que lo cambié. El plan dice que Maven es lo recomendado pero Gradle también es válido.

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

**Cómo quedó en la HU-04:** en la HU-03 las dejé vacías y ahora tienen solo los métodos que pide esta historia. `PersonaRepository` extiende `JpaRepository` y agrega `existsByEmail`.

## 4. Inyección de dependencias por constructor

**Decisión:** todas las dependencias entran por el constructor, con campos `final`. No uso `@Autowired` en los campos.

**Por qué:**
- Se ve claro qué necesita cada clase para funcionar.
- Puedo usar `final`, así la dependencia no cambia después de crear el objeto.
- Puedo crear la clase en un test sin levantar Spring.

## 5. Records para los DTOs

**Decisión:** las respuestas de la API son `record` (por ejemplo `HealthResponse` y `BienvenidaResponse`) y no `Map`. Los DTOs de personas siguen la misma regla.

**Por qué:** un `Map` no tiene un contrato claro. Si escribo mal el nombre de una llave, compila igual y el error solo aparece cuando alguien prueba el endpoint. Con un `record` los campos están definidos, un error de escritura no compila y el orden del JSON es el mismo en que declaro los campos. Además son inmutables y se escriben en pocas líneas.

## 6. `Persona` es una clase y no un record

**Decisión:** `Persona` es una clase normal.

**Por qué:** JPA necesita una clase que pueda modificar para usarla como entidad. En la HU-03 la dejé como clase pensando en esto, y en la HU-04 ya la convertí en entidad. Los DTOs sí son records porque solo transportan datos.

## 7. Un controller por responsabilidad

**Decisión:** `HealthController` (¿está viva la API?) y `BienvenidaController` (el saludo) están separados, y `PersonaController` se encarga solo de las personas.

**Por qué:** uno es operativo y el otro es funcional. Separarlos hace que cada clase tenga un solo motivo para cambiar. Antes los tenía juntos en `InicioController`, pero ese nombre no describía ninguno de los dos.

## 8. Nombres de paquetes y de clases

**Decisión:** los paquetes van en minúsculas y sin guiones bajos (`co.ceiba.gestionpersonas`). Las clases van en PascalCase y los métodos en camelCase.

**Por qué:** es lo que dice la Google Java Style Guide. Spring Initializr había generado el paquete con guiones bajos, y preferí corregirlo temprano porque cada archivo nuevo hace el cambio más costoso.

## 9. Forma de trabajar

**Decisión:** una rama por historia (`feature/HU-XX-descripcion`), commits descriptivos y Pull Request con la plantilla del equipo.

**Por qué:** así cada historia se revisa por separado y queda escrito por qué se hizo cada cosa. Antes de pedir revisión marco la Definition of Done ítem por ítem en el PR.

## 10. Cómo pruebo la API

**Decisión:** uso un archivo `pruebas.http` dentro de `GestionPersonasBackend/`, con la variable `@baseUrl`. También se puede probar con Postman o curl.

**Por qué:** queda guardado en el repositorio y cualquiera puede probar los mismos endpoints. Con la variable no repito la URL en cada petición.

## 11. Base de datos con PostgreSQL y Spring Data JPA

**Decisión:** guardo las personas en PostgreSQL y accedo a los datos con Spring Data JPA.

**Por qué:** es la base de datos que usa el proyecto y JPA me evita escribir las consultas básicas a mano. El repositorio solo declara lo que necesita (como `existsByEmail`) y Spring hace el resto.

## 12. Esquema con migraciones de Flyway

**Decisión:** la tabla se crea con una migración de Flyway versionada (`V1`) y dejé `ddl-auto` en `validate`, no en `update`.

**Por qué:** así el esquema queda escrito en el repositorio y no depende de lo que Hibernate decida crear. Si algún día la entidad no coincide con la tabla, la aplicación avisa al arrancar en vez de cambiar la base de datos sin que me dé cuenta.

## 13. Email único en el servicio y en la base de datos

**Decisión:** reviso que el email no exista con `existsByEmail` en el servicio y además la tabla tiene una restricción `UNIQUE`.

**Por qué:** el servicio permite devolver un mensaje claro (409), pero si dos peticiones con el mismo email llegan al mismo tiempo, las dos podrían pasar esa revisión. La restricción de la base de datos cubre ese caso.

## 14. Validación de los datos de entrada

**Decisión:** valido los datos que llegan por el DTO: nombre y apellido no vacíos, email con formato válido y fecha de nacimiento que no sea futura.

**Por qué:** así los datos incorrectos se rechazan antes de llegar al servicio y la lógica de negocio solo se ocupa de lo que depende de la base de datos, como el email repetido.

## 15. Manejo de errores en un solo lugar

**Decisión:** un manejador global de excepciones (`@RestControllerAdvice`) que responde `400` cuando falla una validación y `409` cuando el email ya existe.

**Por qué:** si cada controller manejara sus errores, el formato de las respuestas sería distinto en cada uno. Con el manejador global todos los errores salen igual y el controller no se llena de `try/catch`.

## 16. Configuración sin secretos

**Decisión:** la conexión a PostgreSQL se define en `application.yml` con variables de entorno.

**Por qué:** el usuario y la contraseña no deben quedar en GitHub. Cada persona define los suyos en su computador.

## 17. Tests del servicio

**Decisión:** empecé con un test unitario del servicio para el camino feliz.

**Por qué:** el servicio es donde está la lógica, y al inyectar por constructor puedo probarlo sin levantar Spring ni la base de datos. Más adelante agrego los casos de error.

## ⏳ Decisiones pendientes

Estas todavía no las he tomado porque no llegan hasta las próximas historias:

- **Manejo de errores para personas que no existen:** cómo responder cuando se busca una persona que no está (HU-05 en adelante).
- **Más tests:** casos de error del servicio y tests del controller.