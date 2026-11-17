# Refinamiento HU-06: Buscar persona por ID

Estado: ✅ Fusionada en `main`. Este refinamiento lo escribí antes de implementar y lo dejé actualizado con lo que pasó al hacer el código.

## Qué necesitaba entender antes
- La HU-05 ya está en main, así que el repositorio, el servicio, el controlador y el DTO de respuesta ya existen y esta historia agrega un método a cada uno.
- Cómo funciona `Optional` y por qué es mejor que devolver `null`.
- Cómo hace un `@ControllerAdvice` para convertir una excepción en una respuesta HTTP.
- Cuál excepción lanza Spring cuando el id de la URL no tiene el tipo esperado (por ejemplo `/api/personas/abc`).

## Estimación
3 puntos.

## Riesgos
- Que ya exista un `@ControllerAdvice` de la HU-04. Resultado: ya existía el `@RestControllerAdvice` (`GlobalExceptionHandler`), así que lo reutilicé y agregué ahí los casos nuevos, sin crear otro.
- Que el id inválido no devuelva 400 sino un error distinto. Resultado: lo probé con Insomnia y `/api/personas/abc` devuelve 400.

## Decisiones que tomé
- `Optional<Persona>` y no `null`: el tipo avisa que puede no haber resultado y obliga a quien llama a decidir qué hacer. Con `null` el método no avisa nada y el compilador tampoco.
- El servicio usa `orElseThrow` con `PersonaNoEncontradaException`, sin `.get()` sin verificar.
- El controlador devuelve el DTO de respuesta, no la entidad.
- El 404 y el 400 los resuelve un `@ControllerAdvice` global y no el controlador, para que el controlador quede limpio.
- El mensaje del 404 incluye el id que se buscó ("No existe una persona con el id 99"), para que el error sea claro.

## Dudas
Ninguna abierta.