# HU-02: Primer endpoint REST

## Historia
COMO usuario del sistema
QUIERO acceder a un endpoint que retorne un mensaje de bienvenida
PARA verificar que la API está funcionando correctamente

## ¿Qué hace?

Permite comprobar que la API está viva con `GET /api/health` y recibir un saludo personalizado con `GET /api/welcome/{nombre}`. Las dos respuestas son JSON con código 200.

## Refinamiento

Qué incluye: los dos endpoints, sus respuestas tipadas con `record` y el archivo `pruebas.http` para probarlos.

Qué no incluye: base de datos, validaciones, tests automatizados (entran en el Mes 2) y cualquier endpoint de personas.

Cómo sé que está lista (criterios de aceptación):
- `GET /api/health` retorna `{"status": "OK", "message": "API funcionando"}`
- `GET /api/welcome/{nombre}` retorna un saludo personalizado
- Las respuestas son JSON con `Content-Type: application/json`
- El código HTTP es 200 en las respuestas exitosas
- Se entrega por Pull Request con la plantilla del equipo
- Hay al menos 3 commits con mensajes descriptivos
- La DoD Nivel 1 está verificada y marcada en el PR

Qué necesitaba antes: el entorno de la HU-01 funcionando y el proyecto arrancando.

Dudas o riesgos (y qué acordé):
- ¿Qué texto lleva el saludo? El criterio solo dice "personalizado". Elegí: "Hola, {nombre}. Te damos la bienvenida a la API de gestión de personas."
- ¿Qué pasa si el nombre trae espacios o tildes? Spring decodifica la URL, así que `Ana%20María` se saluda como "Ana María".
- ¿Qué pasa con `/api/welcome/` sin nombre? No existe esa ruta y Spring responde 404.

Estimación: 3 puntos.

## Decisiones que tomé
- Usé `record` (`HealthResponse`, `BienvenidaResponse`) en lugar de `Map`: el contrato queda tipado, el orden de las llaves lo fija la declaración y un error de dedo en el nombre de un campo no compila.
- Separé en dos controllers (`HealthController` y `BienvenidaController`): el health es operativo (¿está viva la API?) y el welcome es funcional. La alternativa era un solo controller con `@RequestMapping("/api")`, pero mezcla dos responsabilidades.
- Renombré el paquete a `co.ceiba.gestionpersonas`, sin guiones bajos, porque la Google Java Style Guide pide solo minúsculas y dígitos.
- Dejé el saludo neutro ("Te damos la bienvenida") para no fijar un género.
- Moví `pruebas.http` dentro de `GestionPersonasBackend/` y usé una variable `@baseUrl` para no repetir la URL.

## Cómo probarlo
1. Ejecutar `./gradlew bootRun` dentro de `GestionPersonasBackend/`.
2. Abrir `pruebas.http` y enviar `GET {{baseUrl}}/api/health`: debe responder 200 con `{"status":"OK","message":"API funcionando"}`.
3. Enviar `GET {{baseUrl}}/api/welcome/Ana`: debe responder 200 con el saludo.

## DoD Nivel 1
- [x] Compila sin errores ni warnings nuevos
- [x] Criterios de aceptación probados a mano (`pruebas.http`)
- [x] Código en GitHub, en la rama `feature/HU-02-primer-endpoint`
- [x] Al menos 3 commits con mensajes descriptivos
- [x] Pull Request abierto con la plantilla del equipo
- [ ] Pull Request aprobado (pendiente de revisión)
- [x] README actualizado (endpoints y `pruebas.http`)