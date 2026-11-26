# HU-08: Eliminar Persona

**Puntos:** 2
**Rol:** Administrador del sistema

## Historia de usuario

COMO administrador del sistema
QUIERO poder eliminar una persona del sistema
PARA mantener el registro limpio de datos obsoletos

## Criterios de aceptación

- [x] DELETE /api/personas/{id}
- [x] Si la persona existe, la elimina y retorna 204 No Content
- [x] Si no existe, retorna 404 Not Found
- [x] La eliminación es física (hard delete); se revisará en la HU-17
- [x] Queda registro en el log de la eliminación, con el id afectado
- [x] Tests: eliminación exitosa y persona inexistente

## Nota

El hard delete de esta historia es una decisión provisional. La HU-17 (Mes 6) la revierte por un cambio de requisito del negocio.
