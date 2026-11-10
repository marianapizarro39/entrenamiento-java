# 📔💗 Bitácora

## 📅 Semana 0 (22 septiembre - 25 septiembre)
- **Aprendí:** los conceptos base de Scrum leyendo el libro "Scrum: el arte de hacer el doble de trabajo en la mitad del tiempo", y cómo está organizado el plan de entrenamiento y la metodología de trabajo.
- **No entendí todavía:** hasta el momento todo está muy claro.
- **Busqué por mi cuenta:** videos sobre metodología; revisé la guía detallada y los planes de entrenamiento completos.
- **Hecho:** creé mi cuenta de GitHub con la cuenta de Ceiba y el repositorio del proyecto.

## 📅 Semana 1 (28 septiembre - 2 octubre)
- **Aprendí:** a diagnosticar el error de `psql` en PowerShell: revisar la carpeta de PostgreSQL desde la terminal con `dir` y ver por qué el comando no se reconocía.
- **No entendí todavía:** por qué `psql` no se reconocía aunque PostgreSQL estaba instalado. Ya lo entendí: la carpeta `bin` de PostgreSQL no estaba en el Path.
- **Busqué por mi cuenta:** dónde queda instalado PostgreSQL en Windows (`C:\Program Files\PostgreSQL\18\bin`).
- **IA:** Gemini me ayudó a saber qué hacer en la consola cuando apareció el error de `psql`, relacionado con el Path. Verifiqué la solución revisando la carpeta de instalación y probando el comando de nuevo.
- **Hecho:** HU-01 (configuración del entorno): PostgreSQL funcionando, proyecto Hola Mundo con Spring Boot y tablero de trabajo.

## 📅 Semana 2 (5 octubre - 9 octubre)
- **Aprendí:** a crear mis primeros endpoints con `@RestController`, `@GetMapping` y `@PathVariable`, y el flujo de trabajo del programa: una rama por historia, commits, Pull Request y revisión.
- **No entendí todavía:** cómo se completa la plantilla del PR y qué va en "Decisiones que tomé".
- **Busqué por mi cuenta:** la plantilla del PR, qué es un endpoint y cómo probar con `pruebas.http`.
- **IA:** Le pedí a la IA que me aclarara cuándo se usa `Map` y cuándo `record` en las respuestas de los endpoints, porque no tenía claro cuál convenía. Me sirvió para entender en qué casos se usa cada uno.
- **Hecho:** primera versión de la HU-02 (`/api/health` y `/api/welcome/{nombre}`) y apertura del Pull Request.

## 📅 Semana 3 (13 octubre - 16 octubre)
- **Aprendí:**
  - Por qué usar `record` en lugar de `Map` en las respuestas: el contrato queda tipado y un error en el nombre de un campo no compila.
  - Por qué separar el health (operativo) del welcome (funcional) en dos controllers.
  - Que los paquetes en Java van en minúsculas y sin guiones bajos (Google Java Style Guide).
  - Que la DoD se marca ítem por ítem en el PR y que el refinamiento se documenta (qué incluye, qué no, dudas, estimación).
  - Para la HU-03: qué hace cada paquete (`controller`, `service`, `repository`, `domain`, `dto`) y por qué la inyección va por constructor y no con `@Autowired`.
  - Con Git: separar el trabajo por historia en ramas distintas usando `git stash`, y subir cada rama por Pull Request, sin tocar `main`.
- **No entendí todavía:**
  - Qué significa la `U` junto al nombre de un archivo en VS Code (archivo nuevo que Git todavía no conoce).
  - La diferencia entre la carpeta del proyecto (`GestionPersonasBackend/`) y el paquete Java (`gestionpersonas`).
  - Por qué el editor marcaba en amarillo los campos `personaService` y `personaRepository`. Ya lo entendí: son campos privados que aún nadie usa y se usarán en la HU-04.
- **Busqué por mi cuenta:** la regla de nombres de paquetes en la Google Java Style Guide; comandos de Git (`git mv`, `git restore`, `git stash`, `git status`); cómo ver archivos y carpetas desde la terminal.
- **IA:** Seguí preguntándole a la IA cuál era mejor para mi proyecto, `Map` o `record`, y me ayudó a ver que con `record` el contrato queda tipado. También le pregunté cómo quitar los archivos `.log` que se habían subido al repositorio (`hs_err_pid` y `replay_pid`, que genera la JVM cuando se cae). Me explicó que primero había que borrarlos del repositorio y después crear reglas en el `.gitignore`, que es un archivo donde se escriben los nombres de los archivos que Git debe ignorar, para que no se vuelvan a subir. Verifiqué los endpoints en `pruebas.http` después de cambiar a `record` y revisé con Git que los `.log` ya no estuvieran en el repositorio.
- **Hecho:** recibí la revisión de la HU-02 con cambios solicitados. Corregí records, controllers, paquete, `pruebas.http` y la documentación; terminé la HU-03 y avancé en la HU-04.
  
## 📅 Semana 4 (19 octubre - 23 octubre)
- **Aprendí:**
  - A guardar personas en PostgreSQL con Spring Data JPA y a crear la tabla con una migración de Flyway, en vez de dejar que Hibernate la cree (`ddl-auto: validate`).
  - A validar los datos de `POST /api/personas` y a devolver 400 cuando los datos no son válidos y 409 cuando el email ya existe, con un `@RestControllerAdvice`.
  - Que los datos de conexión a la base de datos van en variables de entorno y no escritos en el repositorio.
  - Con Git: integrar varios Pull Request en `main` en orden, resolver conflictos en `README.md` y `decisiones.md` (con `--ours` y `--theirs`) y por qué la rama debe tener lo mismo que probé en mi computador.
  - A decidir y documentar lo que no estaba definido: el email se guarda en minúsculas, la fecha de nacimiento es obligatoria y el 409 de datos duplicados lleva un mensaje genérico.
- **No entendí todavía:** qué significa la X roja de la terminal cuando un comando no muestra nada, como `git grep` sin resultados. Ya lo entendí: no es un error, el comando termina con un código distinto de cero cuando no encuentra nada.
- **IA:** Gemini me guió en los ajustes de la revisión, y me ayudó a redactar la documentación de las historias. Lo que verifiqué yo: que no quedaran marcas de conflicto con `git grep`, que no hubiera `.log` en el repositorio, que `decisiones.md` tuviera sus 18 secciones, que compilara y pasara el test del servicio, y que el `./gradlew build` completo pasara contra una base de prueba vacía en PostgreSQL. Las decisiones del email, la fecha y el mensaje del 409 las tomé yo.
- **Hecho:** HU-04 (registrar personas, PR #29).

## 📅 Semana 5 (26 octubre - 30 octubre)
- **Aprendí:**
  - A listar personas con `GET /api/personas` devolviendo DTOs y no entidades, y a mapear de entidad a DTO con la API de Streams.
  - A ordenar en la base de datos y no en memoria: con `findAllByOrderByApellidoAsc()` el orden lo resuelve un `ORDER BY`, no un `sort()` en Java. Lo comprobé activando `show-sql` y viendo el SQL que genera Hibernate.
  - Que una lista vacía debe responder `200 OK` con `[]` y no un 404, porque que no haya registros no es un error.
- **No entendí todavía:** hasta el momento todo está muy claro en esta historia.
- **Busqué por mi cuenta:** cómo activar `show-sql` y `format_sql` en Spring Boot, y cómo Spring Data JPA arma consultas a partir del nombre del método.
- **IA:** Gemini me ayudó a redactar una parte de la bitácora de la HU-05 y me ayudo a saber cual era el orden correcto para tomar las capturas de evidencia. Lo que verifiqué yo: el `ORDER BY` en la consola de Hibernate, que la lista vacía respondiera `200` con `[]` y que el test del servicio pasara.
- **Hecho:** HU-05 (listar personas) con endpoint, DTO, ordenamiento en base de datos y tests unitarios del servicio.
  
## 📅 Semana 6 (2 noviembre - 6 noviembre)
- **Aprendí:**
  - A buscar una persona con `Optional<Persona>` y `orElseThrow`, lanzando `PersonaNoEncontradaException` en vez de devolver `null` o llamar a `.get()` sin verificar.
  - Por qué `Optional` y no `null`: `null` no aparece en la firma del método, así que quien lo llama no sabe que debe comprobarlo y el compilador no avisa. `Optional<Persona>` lo dice en el tipo y obliga a manejar la ausencia.
- **No entendí todavía:** cómo dejar el `@RestControllerAdvice` para que traduzca bien el 400 cuando el id tiene formato inválido, y cómo probarlo en los tests.
- **Busqué por mi cuenta:** qué es `Optional` en Java y cómo se usa `orElseThrow`; qué excepción lanza Spring cuando el id no se puede convertir a número (`MethodArgumentTypeMismatchException`).
- **IA:** Gemini me ayudó a estructurar una parte de la bitácora de la HU-06 hasta el momento solo ha sido eso. Lo que verifiqué yo: que el endpoint responda en los casos que ya implementé.
- **Hecho:** el viernes 6 integré los PR #23, #25, #29, #28 y #27 en `main`. En la rama `fix/pendientes-semanas-2-a-4` corregí los ajustes de la revisión: la configuración de `application.yml`, los mensajes, el `nombre` obligatorio, el test del email repetido, las peticiones de `pruebas.http`, la migración V2 y la documentación de las tres historias. También avancé en la HU-06 (buscar persona por id) con la excepción propia y el servicio con `orElseThrow`. **Pendiente:** terminar el manejador global (404 y 400) y los tests de caso encontrado y no encontrado. La HU-06 va en su propia rama, creada desde `main` actualizado, con su propio PR.
