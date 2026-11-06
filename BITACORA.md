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
- **Hecho:** primera versión de la HU-02 (`/api/health` y `/api/welcome/{nombre}`) y apertura del Pull Request.

## 📅 Semana 3 (13 octubre - 23 octubre)
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
- **Hecho:** recibí la revisión de la HU-02 con cambios solicitados. Corregí records, controllers, paquete, `pruebas.http` y la documentación; terminé la HU-03 y avancé en la HU-04.