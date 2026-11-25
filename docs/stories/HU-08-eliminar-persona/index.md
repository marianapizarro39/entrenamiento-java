# HU-08: Eliminar Persona

| | |
|---|---|
| Estado | ✅ Fusionada en `main` |
| Pull request | #34 |
| Rama | `feature/HU-08-eliminar-persona |

## Documentos

- [Historia de usuario](historia.md)
- [Refinamiento](refinamiento.md)

## Resumen

Endpoint DELETE /api/personas/{id} que elimina físicamente una persona (204 si existe, 404 si no) y deja en el log el id eliminado. El hard delete es provisional y se revierte en la HU-17.

## Pruebas

- Unitarias: eliminación exitosa y persona inexistente (PersonaServiceTest)
- Manuales: Insomnia / pruebas.http

## Evidencia

- [ ] Captura de los tests pasando
- [ ] Captura del 204 en Insomnia
- [ ] Captura del 404 en Insomnia
- [ ] Captura del log con el id eliminado

## Definition of Done

- [ ] Criterios de aceptación cumplidos
- [ ] Tests pasando
- [ ] Documentación actualizada (api.md, decisiones.md, pruebas.http, índice general, bitácora)
- [ ] DoD marcada sobre la rama subida y verificada clonándola limpia
- [ ] DoD incluida en la descripción del PR