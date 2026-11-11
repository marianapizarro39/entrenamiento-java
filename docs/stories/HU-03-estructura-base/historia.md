# HU-03: Estructura base del proyecto CRUD Personas

## Historia

COMO arquitecto del proyecto

QUIERO tener la estructura base del proyecto de gestión de personas

PARA establecer las capas que seguirán los principios de arquitectura limpia

## ¿Qué hace?

Deja lista la base del proyecto de gestión de personas: los paquetes, la clase `Persona` y las interfaces principales. Todavía no tiene lógica, es el esqueleto sobre el que voy a construir las siguientes historias.

## Qué incluye

Los paquetes, la clase `Persona`, las interfaces `PersonaService` y `PersonaRepository`, el README del proyecto y el `application.yml` con la configuración básica.

## Qué no incluye

La lógica de las capas, base de datos, validaciones y tests. Eso se ve más adelante.

## Criterios de aceptación

- Existen los paquetes `controller`, `service`, `repository`, `domain` y `dto`
- La clase `Persona` tiene `id`, `nombre`, `apellido`, `email` y `fechaNacimiento`
- Las interfaces `PersonaService` y `PersonaRepository` están declaradas
- Las dependencias se inyectan por constructor y no con `@Autowired` en los campos
- El README del proyecto explica qué es y cómo ejecutarlo
- El `application.yml` tiene configuración básica y no tiene secretos
- En el PR explico por qué existe cada paquete
