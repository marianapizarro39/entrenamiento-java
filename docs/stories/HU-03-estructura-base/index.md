# HU-03: Estructura base del proyecto CRUD Personas

| | |
|---|---|
| Estado | ✅ Fusionada en `main` |
| Pull Request | #25 |
| Rama | `feature/HU-03-estructura-base` |

## Documentos

- [historia.md](historia.md): qué se pidió y los criterios de aceptación
- [refinamiento.md](refinamiento.md): dudas, decisiones y el porqué de cada paquete

## Cómo probarlo

1. Abrir el proyecto y esperar a que Gradle descargue las dependencias.
2. Ejecutar `./gradlew build` y revisar que compile sin errores.
3. Levantar la aplicación con `./gradlew bootRun` y revisar que arranque.
4. Probar `GET /api/health` desde `pruebas.http` y comprobar que sigue respondiendo 200 (la HU-02 no se rompió).
5. Revisar que existen los cinco paquetes y que ninguna clase usa `@Autowired`.

## DoD Nivel 1

- [x] Compila sin errores ni warnings nuevos. Nota: el editor marca 2 campos privados sin usar (`personaService` en `PersonaController` y `personaRepository` en `PersonaServiceImpl`); se usarán en la HU-04.
- [x] Criterios de aceptación probados a mano
- [x] Código en GitHub, en la rama `feature/HU-03-estructura-base`
- [x] Al menos 3 commits con mensajes descriptivos
- [x] Pull Request abierto con la plantilla del equipo
- [x] Pull Request aprobado
- [x] README actualizado
