# Refinamiento de la HU-02

Historia: [historia.md](historia.md) · Estado: [index.md](index.md)

## Qué necesitaba antes

El entorno de la HU-01 funcionando y el proyecto arrancando.

## Dudas o riesgos (y qué acordé)

- ¿Qué texto lleva el saludo? El criterio solo dice "personalizado". Elegí: "Hola, {nombre}. Te damos la bienvenida a la API de gestión de personas."
- ¿Qué pasa si el nombre trae espacios o tildes? Spring decodifica la URL, así que `Ana%20María` se saluda como "Ana María".
- ¿Qué pasa con `/api/welcome/` sin nombre? No existe esa ruta y Spring responde 404.

## Estimación

3 puntos.

## Decisiones que tomé

- Usé `record` (`HealthResponse`, `BienvenidaResponse`) en lugar de `Map`: el contrato queda tipado, el orden de las llaves lo fija la declaración y un error de dedo en el nombre de un campo no compila.
- Separé en dos controllers (`HealthController` y `BienvenidaController`): el health es operativo (¿está viva la API?) y el welcome es funcional. La alternativa era un solo controller con `@RequestMapping("/api")`, pero mezcla dos responsabilidades.
- Renombré el paquete a `co.ceiba.gestionpersonas`, sin guiones bajos, porque la Google Java Style Guide pide solo minúsculas y dígitos.
- Dejé el saludo neutro ("Te damos la bienvenida") para no fijar un género.
- Moví `pruebas.http` dentro de `GestionPersonasBackend/` y usé una variable `@baseUrl` para no repetir la URL.
