# Refinamiento HU-09: Refactorización Clean Code

**Estado:** 🚧 En progreso

## Qué se va a hacer

Revisar mi propio código de `PersonaServiceImpl` y `PersonaController`, identificar code smells con el nombre del catálogo de Refactoring Guru y corregirlos con refactorings nombrados. No se cambia el comportamiento: los tests existentes tienen que pasar sin modificarlos.

## Code smells y refactorings

| # | Code smell | Dónde está | Refactoring |
|---|---|---|---|
| 1 | Duplicate Code | La normalización del email se repite en `crear` y en `actualizar` | Extract Method (`normalizarEmail`) |
| 2 | Dead Code | Imports sin usar y `import *` en `PersonaController` | Eliminar imports sin usar y dejar imports explícitos |
| 3 | (CONFIRMAR) Magic String | Mensajes de log y de error repetidos en el service | Replace Magic Number with Symbolic Constant |

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