# Refinamiento HU-06: Buscar persona por ID

Estado: en desarrollo. Este refinamiento lo escribí antes de implementar, y lo voy actualizando si algo cambia mientras hago el código.

## Qué necesitaba entender antes
- La HU-05 ya está en main, así que el repositorio, el servicio, el controlador y el DTO de respuesta ya existen y esta historia agrega un método a cada uno.
- Cómo funciona `Optional` y por qué es mejor que devolver `null`.
- Cómo hace un `@ControllerAdvice` para convertir una excepción en una respuesta HTTP.
- Cuál excepción lanza Spring cuando el id de la URL no tiene el tipo esperado (por ejemplo `/api/personas/abc`).

## Estimación
3 puntos.

## Riesgos
- Que ya exista un `@ControllerAdvice` de la HU-04. Si existe, agrego los casos nuevos ahí y no creo otro.
- Que el id inválido no devuelva 400 sino un error distinto. Lo voy a probar con Insomnia.

## Decisiones que tomé
- `Optional<Persona>` y no `null`: el tipo avisa que puede no haber resultado y obliga a quien llama a decidir qué hacer. Con `null` el método no avisa nada y el compilador tampoco.
- El servicio usa `orElseThrow` con `PersonaNoEncontradaException`, sin `.get()` sin verificar.
- El controlador devuelve el DTO de respuesta, no la entidad.
- El 404 y el 400 los resuelve un `@ControllerAdvice` global y no el controlador, para que el controlador quede limpio.

## Dudas
- Si el mensaje del 404 debe incluir el id que se buscó. Pienso que sí, para que sea claro.