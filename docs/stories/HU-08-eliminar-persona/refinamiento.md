# Refinamiento HU-08: Eliminar Persona

**Estado:** 🔁 En desarrollo

## Qué se va a hacer

Un endpoint DELETE /api/personas/{id} que borra la persona de la base de datos. No recibe body. Responde 204 sin contenido si la borró y 404 si el id no existe.

## Decisiones

1. **Borrado físico (hard delete).** La historia lo pide así. Es provisional: en la HU-17 se cambia a borrado lógico, por eso conviene dejar la lógica de eliminar en un solo método del service, para que el cambio después sea fácil.
2. **Verificar que exista antes de borrar.** No me puedo apoyar solo en deleteById, porque en las versiones nuevas de Spring Data JPA no lanza error si el id no existe y no podría devolver el 404. Primero reviso con existsById (o buscando la persona) y, si no está, lanzo la misma excepción de "no encontrada" que ya uso en buscar por id y actualizar.
3. **Transaccionalidad.** El método eliminar del service va con @Transactional, para que la verificación y el borrado queden en la misma transacción.
4. **Log.** Un log a nivel INFO cuando se elimina, con solo el id (ej: "Persona eliminada con id: 13"). No se registran nombre, correo ni otros datos personales. Para el caso de inexistente basta con el 404; si se quiere, un WARN con el id.
5. **Respuesta.** El controller devuelve ResponseEntity<Void> con 204. El 404 lo maneja el manejador global de excepciones que ya existe.
6. **Segunda llamada al mismo id.** Devuelve 404, porque ya no existe. Lo dejo documentado en api.md.

## Fuera de alcance

- Borrado lógico (HU-17)
- Eliminar varias personas a la vez
- Autenticación o roles del administrador

## Riesgos

- Si en algún momento otra tabla referencia a personas, el borrado podría fallar por llave foránea. Hoy no debería pasar, pero lo reviso antes de probar.
- Como el borrado es definitivo, en las pruebas manuales no uso personas que quiera conservar.

## Plan de tareas

1. Agregar el método eliminar(id) en el service con la validación y el log
2. Agregar el endpoint DELETE en el controller
3. Tests en PersonaServiceTest: eliminación exitosa y persona inexistente
4. Prueba manual en Insomnia (204 y luego 404 con el mismo id)
5. Actualizar api.md, decisiones.md, pruebas.http, índice general y bitácora
6. Marcar la DoD sobre la rama subida

## Casos de prueba

| Caso | Entrada | Resultado esperado |
|---|---|---|
| Éxito | DELETE /api/personas/{id existente} | 204, sin body, la persona ya no está en la BD, se registra el log con el id |
| Inexistente | DELETE /api/personas/9999 | 404 Not Found, no se borra nada |
| Repetir borrado | DELETE al mismo id dos veces | 204 la primera y 404 la segunda |

## Preguntas abiertas

- ¿El 404 debe llevar el mismo formato de error que ya devuelven las otras historias? (Lo tengo asumido que sí.)