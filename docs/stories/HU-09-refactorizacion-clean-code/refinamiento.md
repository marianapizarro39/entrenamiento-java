# Refinamiento HU-09: Refactorización Clean Code

**Estado:** 🚧 En progreso

## Qué se va a hacer

Revisar mi propio código de `PersonaServiceImpl` y `PersonaController`, identificar code smells con el nombre del catálogo de Refactoring Guru y corregirlos con refactorings nombrados. No se cambia el comportamiento: los tests existentes tienen que pasar sin modificarlos.

## Code smells y refactorings

| # | Code smell (Refactoring Guru) | Dónde está | Refactoring |
|---|---|---|---|
| 1 | Duplicate Code | `request.email().trim().toLowerCase(Locale.ROOT)` repetido en `crear` y `actualizar` | Extract Method (`normalizarEmail`) |
| 2 | Duplicate Code | La búsqueda con `findById` + `orElseThrow` + log `warn` está repetida en `actualizar` y `eliminar` | Extract Method (`buscarPersonaOLanzarError`) |
| 3 | Comments | El comentario "Solo hay conflicto si el email lo tiene OTRA persona" explica qué hace el `if` | Extract Method (`emailLoTieneOtraPersona`), el nombre del método reemplaza el comentario |

## Limpieza adicional

- `PersonaController` usa `import org.springframework.web.bind.annotation.*`. Lo cambio por imports explícitos (`@RestController`, `@RequestMapping`, `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`, `@PathVariable`, `@RequestBody`).

## Decisiones

1. **Un commit por refactoring**, con el nombre del refactoring en el mensaje.
2. **Correr los tests después de cada refactoring** (`./gradlew test`). Si alguno falla, deshago el cambio.
3. **Los tests no se tocan.** Si hay que modificarlos, no era una refactorización.
4. **Solo capa de servicio y controladores.** El resto del proyecto queda igual.

## Fuera de alcance

- Cambiar el comportamiento de los endpoints
- Refactorizar repositorios, DTOs o migraciones
- Agregar funcionalidades nuevas

## Riesgos

- Que un refactoring cambie el comportamiento sin que me dé cuenta. Por eso corro los tests después de cada uno.

## Plan de tareas

1. Refinamiento (este commit)
2. Bitácora de la semana 9
3. Formato con Shift + Alt + F en `PersonaController` y `PersonaServiceImpl`
4. Un commit por refactoring, con tests después de cada uno
5. Completar la bitácora con los temas de la semana 8
6. Marcar la DoD Nivel 3 sobre la rama subida y verificar con un clon limpio