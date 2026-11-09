# 🔁 Retrospectivas

## Sprint 1 (22 septiembre - 23 octubre)

**Historias del sprint:** HU-01 (configuración del entorno), HU-02 (primer endpoint REST) y HU-03 (estructura base del proyecto). También alcancé a avanzar en la HU-04.

### ✅ Qué salió bien

- Dejé el entorno funcionando: PostgreSQL, el proyecto Hola Mundo con Spring Boot y el tablero de trabajo. Me trabé con el error de `psql`, pero lo resolví entendiendo que faltaba la carpeta `bin` de PostgreSQL en el Path.
- Entregué la HU-02 y apliqué los comentarios de la revisión: `record` en vez de `Map`, dos controllers separados, el paquete en minúsculas y el `pruebas.http`.
- En la HU-03 expliqué por qué existe cada paquete y dejé dudas reales escritas en el refinamiento. En la revisión me dijeron que fue la historia donde mejor se vio mi criterio.
- Aprendí el flujo de trabajo del programa: una rama por historia, commits, Pull Request y revisión.
- Aprendí a separar el trabajo por historia con `git stash` sin tocar `main`.

### ⚠️ Qué no salió bien

- Se subieron al repositorio los archivos `hs_err_pid` y `replay_pid`, que son logs que genera la JVM cuando se cae. Tuve que abrir dos Pull Request para limpiarlos (#24 y #28) y crear reglas en el `.gitignore`.
- Mezclé el trabajo de las historias y después me tocó separarlo en ramas con `git stash`. Al integrar todo tuve conflictos en `README.md` y en `decisiones.md`.
- Escribí el refinamiento después de programar, no antes.
- Dejé la documentación de cada historia en un solo `README.md` y varios archivos de `docs/architecture` quedaron vacíos.
- La descripción del PR de la HU-02 quedó desactualizada (todavía hablaba de `Map.of` y de `InicioController`) y marqué "con la plantilla del equipo" sin tenerla instalada en el repositorio.
- Marqué la DoD sin verificarla bien sobre la rama y resolví hilos de comentarios sin responderlos.
- Tenía sangrías mezcladas (2 y 4 espacios) y archivos sin salto de línea al final.
- El PR #27 con la bitácora se cerró sin fusionar, y por eso esa actualización no estaba en `main`.

### 🔧 Qué voy a mejorar

1. Escribir el refinamiento antes de programar y que el primer commit de la rama sea el de documentación (`docs:`).
2. Crear una rama por historia desde `main` y no mezclar trabajo de varias historias.
3. Revisar `git status` antes de cada `git add` y poner los logs en el `.gitignore` desde el principio.
4. Documentar cada historia en tres archivos: `historia.md`, `refinamiento.md` e `index.md`.
5. Actualizar la descripción del PR con el código tal como quedó y responder cada hilo antes de resolverlo.
6. Verificar la DoD sobre un clon limpio de la rama antes de marcarla.
7. Pasar el formateador del IDE antes de cada commit.
8. Tomar captura cada vez que corra un test, como evidencia para la auditoría.
