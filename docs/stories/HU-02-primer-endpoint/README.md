# HU-02: Primer endpoint REST

## Historia
como usuario del sistema quiero acceder a un endpoint que retorne un mensaje de bienvenida, para verificar que la API está funcionando correctamente. Son 3 puntos.

## ¿Qué hace?

Permite comprobar que la API está encendida y respondiendo antes de construir el resto del sistema. También sirve como mi primera práctica para entender cómo una petición llega a la aplicación y cómo se responde.

## Endpoints
- GET /api/health -> responde que la API está funcionando perfectamente
- GET /api/welcome/{nombre} -> responde un saludo con el nombre 

## Refinamiento 

Qué incluye: los dos endpoints de arriba, con respuesta en JSON y código 200 (OK).

Qué no incluye: autenticación, base de datos, validar el nombre y tests automáticos. Eso se ve más adelante.

Cómo sé que está lista (criterios de aceptación):
- `GET /api/health` responde 200 con `{ "status": "OK", "message": "API funcionando" }`
- `GET /api/welcome/{nombre}` responde 200 con un saludo para ese nombre
- Las dos respuestas son JSON (`Content-Type: application/json`)
- La entrego por Pull Request con la plantilla, mínimo 3 commits descriptivos y la DoD Nivel 1 marcada

Qué necesitaba antes: Java 21, Gradle y Spring Boot ya configurados en la HU-01.

Dudas o riesgos: usé un `Map` para devolver la respuesta  porque es lo más rápido para esta historia. Pero no sé si es lo adecuado o si conviene usar un `Record` desde ahora, pensando en las siguientes historias. Con el `record` los campos y tipos quedan definidos en el código. 

Estimación: 3 puntos.

## Decisiones que tomé
- Usé `@RestController` porque Spring convierte la respuesta a JSON solo y no tengo que configurar nada más.
- Usé `@PathVariable` para el nombre porque es parte de la ruta y la URL queda sencilla.
- Dejé los dos endpoints en el mismo controlador porque son pocos y están relacionados. Separarlos me pareció innecesario para esta historia.
- Usé `Map`para la respuesta porque son solo dos campos y no necesitaba crear una clase aparte para esta historia.

## Cómo probarlo
1. Levantar la API.
2. Abrir `http://localhost:8080/api/health` y revisar que dé el JSON esperado.
3. Abrir `http://localhost:8080/api/welcome/Mariana` y revisar que salude con el nombre.

## DoD Nivel 1
- [x] Compila sin errores ni warnings nuevos
- [x] Criterios de aceptación probados a mano en Postman
- [x] Código en GitHub, en una rama `feature/HU-02-primer-endpoint`
- [x] Al menos 3 commits con mensajes descriptivos
- [x] Pull Request abierto con la plantilla del equipo
- [ ] Pull Request aprobado (pendiente, pedí revisión de nuevo después de estas correcciones)
- [x] README actualizado (no hay pasos nuevos para ejecutar el proyecto)


