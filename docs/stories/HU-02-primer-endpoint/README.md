# HU-02: Primer endopoint REST

## ¿Qué hace?

Permite comprobar que la API está encendida y respondiendo antes de construir el resto del sistema. También sirve como mi primera práctica para entender cómo una petición llega a la aplicación y cómo se responde.

## Endpoints
- GET /api/health -> responde que la API está funcionado perfectamente
- GET /api/welcome/{nombre} -> responde un saludo con el nombre 