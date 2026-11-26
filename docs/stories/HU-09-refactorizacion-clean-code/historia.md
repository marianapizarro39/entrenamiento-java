# HU-09: Refactorización Clean Code

**Puntos:** 3
**Rol:** Desarrollador del equipo

## Historia de usuario

COMO desarrollador del equipo
QUIERO que el código del CRUD cumpla con principios de Clean Code
PARA facilitar el mantenimiento y la legibilidad

## Alcance

Capa de servicio y controladores. No se refactoriza todo el proyecto.

## Criterios de aceptación

- [ ] Identifica y documenta en el PR al menos 3 code smells concretos de mi propio código, nombrados con el vocabulario del catálogo
- [ ] Aplica al menos 3 refactorings nombrados (Extract Method, Rename, Replace Magic Number with Constant, Extract Variable...)
- [ ] Nombres descriptivos, sin abreviaciones ni nombres de una letra
- [ ] Sin código duplicado en la capa de servicio
- [ ] Constantes con nombre en lugar de valores literales repetidos
- [ ] Sin comentarios que expliquen QUÉ hace el código (se permiten los que expliquen POR QUÉ)
- [ ] Imports sin utilizar eliminados; sin imports con asterisco
- [ ] Los tests existentes siguen pasando sin modificación

## Nota

El criterio de los tests sin modificar es el más importante: es la definición de refactorizar. Si hay que cambiar un test, ya no fue una refactorización sino un cambio de comportamiento.