## Historia de Usuario

**HU-XX:** <título de la historia>
**Sprint (mes):** <N>
**Puntos estimados:** <N>  |  **Esfuerzo real percibido:** <bajo / igual / mayor>

## ¿Qué hace este cambio?

<2 a 4 frases en lenguaje de negocio, no técnico. Si no puedes explicarlo sin nombrar clases, probablemente no tienes claro el valor que entrega.>

## ¿Cómo lo verifico?

<Pasos concretos para que el revisor lo pruebe. Incluye la petición HTTP de ejemplo, el body y la respuesta esperada.>

## Decisiones que tomé

<Las alternativas que consideraste y por qué elegiste esta. Si no tomaste ninguna decisión, di eso explícitamente.>

## Dudas o puntos donde quiero retroalimentación

<Sé específica. "Revísame todo" no ayuda.>

## Definition of Done

<Se marca sobre la rama subida a GitHub: si al clonarla limpia el proyecto arranca y el build pasa, se puede marcar. Si un ítem no aplica o no se cumplió, di por qué en lugar de omitirlo.>

### Nivel 1 (desde el Mes 1)

- [ ] El código compila sin errores ni warnings nuevos
- [ ] Los criterios de aceptación de la historia se cumplen y fueron verificados manualmente (Postman / navegador)
- [ ] El código está en GitHub, en una rama con nombre descriptivo (feature/HU-XX-descripcion-corta)
- [ ] Hay al menos 3 commits con mensajes descriptivos (no "cambios", "fix", "asdf")
- [ ] Existe un Pull Request abierto contra main/develop usando la plantilla del equipo
- [ ] El Pull Request fue aprobado
- [ ] El README refleja cualquier paso nuevo necesario para ejecutar el proyecto

### Nivel 2 (desde el Mes 2)

- [ ] La historia tiene al menos UN test automatizado que verifica su comportamiento principal (camino feliz)
- [ ] Los tests existentes siguen pasando (no se rompió nada)
- [ ] No hay credenciales, cadenas de conexión ni secretos en el código versionado
- [ ] Las operaciones de acceso a datos manejan el caso "no encontrado" de forma explícita (no devuelven null silenciosamente)

### Acordado con el equipo

- [ ] Verificado en un clon limpio de la rama: el proyecto arranca y `./gradlew build` pasa
- [ ] La historia tiene sus 3 archivos en `docs/stories/HU-XX-.../`: `historia.md`, `refinamiento.md` e `index.md`
- [ ] El refinamiento se escribió antes de implementar (el primer commit de la rama es el de documentación)
- [ ] Las decisiones nuevas (IA, configuración, stack) están en `docs/architecture/decisiones.md`

## Capturas o evidencia

<Obligatoria, no opcional: habrá auditoría. Captura cada vez que corras un test: el final de `./gradlew build` con `BUILD SUCCESSFUL`, las peticiones HTTP con su respuesta y los tests pasando.>
