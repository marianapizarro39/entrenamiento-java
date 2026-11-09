# Refinamiento de la HU-03

Historia: [historia.md](historia.md) · Estado: [index.md](index.md)

## Qué necesitaba antes

Java 21, Gradle y Spring Boot configurados (HU-01). Esta historia no depende de los endpoints de la HU-02.

## Dudas o riesgos

- Las interfaces `PersonaService` y `PersonaRepository` quedan sin métodos, porque los métodos los pide cada historia que los usa. No sé si está bien dejarlas vacías hasta la HU-04.
- `PersonaServiceImpl` y `PersonaController` no tienen `@Service` ni `@RestController` todavía. Con `@Service`, Spring intentaría crear el bean y fallaría porque `PersonaRepository` aún no tiene implementación.

## Estimación

5 puntos.

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
