# Refinamiento de la HU-05

Historia: [historia.md](historia.md) · Estado: [index.md](index.md)

## Estado
Hecho. Este refinamiento se escribe antes del código.

## Qué necesitaba antes
- La HU-04 (registrar personas) fusionada en `main`, porque necesito personas guardadas para probar el listado.
- El DTO de respuesta de las historias anteriores, para reutilizarlo y no devolver la entidad.
- Activar `spring.jpa.show-sql=true` para poder mostrar el SQL.

## Dudas o riesgos
- **Orden en memoria:** si ordeno con `sort()` en Java, el criterio principal no se cumple. Lo reviso en el SQL de la consola.
- **Apellidos repetidos:** la historia no dice cómo desempatar. Por ahora solo ordeno por apellido.
- **Tildes y mayúsculas:** el orden depende de cómo PostgreSQL compare los textos. Pienso probar con apellidos con tilde.
- **Lista vacía:** tiene que responder 200 con `[]`, no 404.

## Qué acordé
- El orden se resuelve con un método del repositorio (`findAllByOrderByApellidoAsc`), para que Hibernate genere el `ORDER BY`.
- El servicio convierte a DTO con `.stream().map(...).toList()`.
- Sin paginación ni filtros, porque la historia no los pide.

## Estimación
3 puntos.

## Decisiones que tomé
- Orden con tildes (riesgo cerrado). Registré personas con apellidos Álvarez, Arias, Fernández, Osorio y Zapata, en desorden. GET /api/personas devolvió primero "Álvarez", antes de "Arias". Un apellido que empieza con letra con tilde no se va al final: el orden por apellido funciona bien con tildes en esta base de datos. No requiere cambios en esta historia.
