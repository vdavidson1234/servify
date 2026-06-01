# Informe de normalizacion de base de datos - Servify

Fecha: 2026-05-29

## Objetivo

Verificar que el esquema PostgreSQL del MVP quede normalizado y que las relaciones principales no permitan datos inconsistentes. La revision se hizo sobre:

- `init.sql`, usado para crear una base nueva.
- La base real del contenedor Docker `servify_db`.
- Las entidades/adapters JPA que usa el backend.

## Resultado general

La base queda apta para el MVP y cercana a 3FN en las entidades principales:

- Cada tabla tiene PK.
- Las entidades fuertes estan separadas: `usuario`, `perfil_usuario`, `credencial_acceso`, `identidad_externa`, `publicacion_servicio`, `solicitud_servicio`, `distribucion_solicitud`, `asignacion_servicio`, etc.
- Los catalogos/estados usan tablas o enums PostgreSQL.
- Se agregaron FKs y constraints compuestas para evitar inconsistencias en relaciones que guardan datos de trazabilidad.

## Correcciones aplicadas

### Autenticacion

Se elimino la duplicacion de password:

- Antes: `usuario.password_hash` y `credencial_acceso.password_hash`.
- Ahora: el hash queda solo en `credencial_acceso.password_hash`.

Esto respeta mejor la separacion:

- `usuario`: identidad principal y contacto.
- `credencial_acceso`: login local con email/password.
- `identidad_externa`: login por Google/LinkedIn.

Tambien se reforzo `refresh_token`:

- `credencial_acceso_id` puede ser null para login social.
- `identidad_externa_id` puede ser null para login local.
- `chk_refresh_token_origen` exige exactamente uno de los dos origenes.
- Se agregaron FKs compuestas para que el `usuario_id` del token coincida con el usuario de la credencial o identidad externa.

### Login social

Se agrego `identidad_externa` como tabla separada:

- `proveedor`
- `subject`
- `email`
- `email_verificado`
- `nombre_mostrado`
- `fecha_vinculacion`
- `ultimo_acceso`
- `habilitada`

Restricciones clave:

- `UNIQUE (proveedor, subject)`: una cuenta externa no se duplica.
- `UNIQUE (usuario_id, proveedor)`: un usuario no queda con dos cuentas del mismo proveedor.
- FK a `usuario(id)`.

### Solicitudes/asignaciones

Se reforzaron relaciones que antes podian contradecirse:

- `distribucion_solicitud.prestador_id` ahora es obligatorio.
- `asignacion_servicio.prestador_id` y `asignacion_servicio.publicacion_id` ahora son obligatorios.
- `distribucion_solicitud(publicacion_id, prestador_id)` debe coincidir con `publicacion_servicio(id, usuario_id)`.
- `asignacion_servicio(distribucion_id, solicitud_id, publicacion_id, prestador_id)` debe coincidir con la distribucion original.
- `confirmacion_finalizacion(asignacion_servicio_id, solicitud_id)` debe coincidir con la asignacion.
- `contraoferta(distribucion_solicitud_id, prestador_id)` debe coincidir con la distribucion.

Esto mantiene trazabilidad sin permitir que los IDs copiados apunten a entidades incompatibles.

## Archivos relevantes

- `init.sql`: esquema base para una DB nueva.
- `db/patch-social-auth-postgres.sql`: patch incremental para auth social.
- `db/patch-normalizacion-integridad-postgres.sql`: patch incremental de normalizacion e integridad.
- `src/main/java/com/servify/ServifyApplication.java`: corrige la timezone Java a `America/Argentina/Buenos_Aires` para que PostgreSQL acepte la conexion.
- `src/main/java/com/servify/solicitudes/infrastructure/persistence/SolicitudJpaEntities.java`: alinea nullability de asignacion con el esquema.

## Como aplicar en una DB existente

Si el volumen Docker ya existe, `init.sql` no se ejecuta de nuevo automaticamente. Para actualizar esa DB hay que correr:

```powershell
docker cp db\patch-social-auth-postgres.sql servify_db:/tmp/patch-social-auth-postgres.sql
docker exec servify_db psql -U postgres -d Servify -v ON_ERROR_STOP=1 -f /tmp/patch-social-auth-postgres.sql

docker cp db\patch-normalizacion-integridad-postgres.sql servify_db:/tmp/patch-normalizacion-integridad-postgres.sql
docker exec servify_db psql -U postgres -d Servify -v ON_ERROR_STOP=1 -f /tmp/patch-normalizacion-integridad-postgres.sql
```

Para una base nueva, alcanza con levantar Docker desde cero y dejar que ejecute `init.sql`.

## Verificacion realizada

Se verifico:

- La suite automatizada pasa completa: `Tests run: 10, Failures: 0, Errors: 0, Skipped: 0`.
- La aplicacion arranca contra PostgreSQL real con `spring.jpa.hibernate.ddl-auto=validate`.
- Hibernate detecta PostgreSQL 16 y valida el esquema sin errores.

## Observacion pendiente

Hay columnas de trazabilidad que duplican IDs derivables, por ejemplo `asignacion_servicio.solicitud_id` junto con `distribucion_id`. No se eliminaron porque el dominio y los DTOs del MVP las usan directamente. En vez de borrarlas, se agregaron constraints compuestas para que no puedan quedar inconsistentes.
