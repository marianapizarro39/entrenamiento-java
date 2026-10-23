# HU-03: Estructura base del proyecto CRUD Personas

## Historia
COMO arquitecto del proyecto
QUIERO tener la estructura base del proyecto de gestión de personas
PARA establecer las capas que seguirán los principios de arquitectura limpia

## ¿Qué hace?

Deja lista la base del proyecto de gestión de personas: los paquetes, la clase `Persona` y las interfaces principales. Todavía no tiene lógica, es el esqueleto sobre el que voy a construir las siguientes historias.

## Refinamiento

Qué incluye: los paquetes, la clase `Persona`, las interfaces `PersonaService` y `PersonaRepository`, el README del proyecto y el `application.yml` con la configuración básica.

Qué no incluye: la lógica de las capas, base de datos, validaciones y tests. Eso se ve más adelante.

Cómo sé que está lista (criterios de aceptación):
- Existen los paquetes `controller`, `service`, `repository`, `domain` y `dto`
- La clase `Persona` tiene `id`, `nombre`, `apellido`, `email` y `fechaNacimiento`
- Las interfaces `PersonaService` y `PersonaRepository` están declaradas
- Las dependencias se inyectan por constructor y no con `@Autowired` en los campos
- El README del proyecto explica qué es y cómo ejecutarlo
- El `application.yml` tiene configuración básica y no tiene secretos
- En el PR explico por qué existe cada paquete

Qué necesitaba antes: Java 21, Gradle y Spring Boot configurados (HU-01). Esta historia no depende de los endpoints de la HU-02.

Dudas o riesgos:
- Las interfaces `PersonaService` y `PersonaRepository` quedan sin métodos, porque los métodos los pide cada historia que los usa. No sé si está bien dejarlas vacías hasta la HU-04.
- `PersonaServiceImpl` y `PersonaController` no tienen `@Service` ni `@RestController` todavía. Con `@Service`, Spring intentaría crear el bean y fallaría porque `PersonaRepository` aún no tiene implementación.

Estimación: 5 puntos.

## Por qué existe cada paquete
- `controller`: recibe las peticiones HTTP y devuelve la respuesta. No tiene lógica de negocio, solo le pasa el trabajo al servicio.
- `service`: aquí va la lógica de negocio, o sea lo que se hace con los datos. Su implementación va en `service.impl`, separada de la interfaz.
- `repository`: es el que guarda y consulta los datos. Así el resto del proyecto no depende de dónde ni cómo se guardan.
- `domain`: tiene las clases que representan el negocio, como `Persona`. Es lo más importante del proyecto y no depende de los otros paquetes.
- `dto`: son los objetos que viajan por la API. Sirven para no mostrar directamente la clase del dominio y poder cambiar una sin dañar la otra.

## Decisiones que tomé
- Inyecté las dependencias por constructor y no con `@Autowired` en el campo, porque así se ve claro qué necesita cada clase, puedo usar `final` y se puede probar sin levantar Spring.
- Dejé `PersonaService` y `PersonaRepository` como interfaces para que las capas dependan de contratos y no de una implementación.
- Dejé `PersonaServiceImpl` y `PersonaController` sin anotaciones de Spring hasta la HU-04, para que la aplicación siga arrancando. Existen para demostrar la inyección por constructor.
- `Persona` es una clase y no un `record`, porque en el Mes 2 será una entidad JPA.
- No agregué métodos ni lógica a las interfaces: solo lo que pide la historia.

## Cómo probarlo
1. Abrir el proyecto y esperar a que Gradle descargue las dependencias.
2. Ejecutar `./gradlew build` y revisar que compile sin errores.
3. Levantar la aplicación con `./gradlew bootRun` y revisar que arranque.
4. Probar `GET /api/health` desde `pruebas.http` y comprobar que sigue respondiendo 200 (la HU-02 no se rompió).
5. Revisar que existen los cinco paquetes y que ninguna clase usa `@Autowired`.

## DoD Nivel 1
- [x] Compila sin errores ni warnings nuevos. Nota: el editor marca 2 campos privados sin usar (`personaService` en `PersonaController` y `personaRepository` en `PersonaServiceImpl`); se usarán en la HU-04.
- [x] Criterios de aceptación probados a mano
- [x] Código en GitHub, en la rama `feature/HU-03-estructura-base`
- [x] Al menos 3 commits con mensajes descriptivos
- [x] Pull Request abierto con la plantilla del equipo
- [ ] Pull Request aprobado (pendiente de revisión)
- [x] README actualizado