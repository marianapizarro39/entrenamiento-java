# HU-04: Registrar personas

| | |
|---|---|
| Estado | ✅ Fusionada en `main` |
| Pull Request | #29 |
| Rama | `feature/HU-04-registrar-personas` |

## Documentos

- [historia.md](historia.md): qué se pidió y los criterios de aceptación
- [refinamiento.md](refinamiento.md): por qué existe cada pieza y las decisiones
- [Contrato del endpoint](../../components/backend/api.md)

## Cómo probarlo

1. Levantar PostgreSQL y definir las variables de entorno `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD`.
2. Ejecutar `./gradlew build` dentro de `GestionPersonasBackend/`. Debe terminar en `BUILD SUCCESSFUL`, con los tests pasando.
3. Ejecutar la aplicación con `./gradlew bootRun` y probar con Postman, curl o `pruebas.http`:
   - `POST /api/personas` con datos válidos → `201` con el `id` generado
   - `POST` con nombre vacío, email inválido, fecha futura o sin fecha → `400` con mensajes
   - `POST` repitiendo un email (también con otras mayúsculas) → `409`
4. Probar `GET /api/health` y comprobar que sigue respondiendo `200`.

## DoD

Esta historia se verifica con la DoD Nivel 2 en el PR de corrección.
