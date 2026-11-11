# HU-02: Primer endpoint REST

## Historia

COMO usuario del sistema

QUIERO acceder a un endpoint que retorne un mensaje de bienvenida

PARA verificar que la API está funcionando correctamente

## ¿Qué hace?

Permite comprobar que la API está viva con `GET /api/health` y recibir un saludo personalizado con `GET /api/welcome/{nombre}`. Las dos respuestas son JSON con código 200.

## Qué incluye

Los dos endpoints, sus respuestas tipadas con `record` y el archivo `pruebas.http` para probarlos.

## Qué no incluye

Base de datos, validaciones, tests automatizados (entran en el Mes 2) y cualquier endpoint de personas.

## Criterios de aceptación

- `GET /api/health` retorna `{"status": "OK", "message": "API funcionando"}`
- `GET /api/welcome/{nombre}` retorna un saludo personalizado
- Las respuestas son JSON con `Content-Type: application/json`
- El código HTTP es 200 en las respuestas exitosas
- Se entrega por Pull Request con la plantilla del equipo
- Hay al menos 3 commits con mensajes descriptivos
- La DoD Nivel 1 está verificada y marcada en el PR
