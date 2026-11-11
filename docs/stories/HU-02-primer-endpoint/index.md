# HU-02: Primer endpoint REST

| | |
|---|---|
| Estado | ✅ Fusionada en `main` |
| Pull Request | #23 |
| Rama | `feature/HU-02-primer-endpoint` |

## Documentos

- [historia.md](historia.md): qué se pidió y los criterios de aceptación
- [refinamiento.md](refinamiento.md): dudas y decisiones

## Cómo probarlo

1. Ejecutar `./gradlew bootRun` dentro de `GestionPersonasBackend/`.
2. Abrir `pruebas.http` y enviar `GET {{baseUrl}}/api/health`: debe responder 200 con `{"status":"OK","message":"API funcionando"}`.
3. Enviar `GET {{baseUrl}}/api/welcome/Ana`: debe responder 200 con el saludo.

## DoD Nivel 1

- [x] Compila sin errores ni warnings nuevos
- [x] Criterios de aceptación probados a mano (`pruebas.http`)
- [x] Código en GitHub, en la rama `feature/HU-02-primer-endpoint`
- [x] Al menos 3 commits con mensajes descriptivos
- [x] Pull Request abierto con la plantilla del equipo
- [x] Pull Request aprobado
- [x] README actualizado (endpoints y `pruebas.http`)
