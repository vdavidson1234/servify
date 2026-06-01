# Guia API React MVP - Servify

Fecha: 30 de mayo de 2026

## Base URL

Backend local:

```text
http://localhost:8080/api/v1
```

Ejemplo con Axios:

```javascript
import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080/api/v1",
  headers: {
    "Content-Type": "application/json"
  }
});
```

CORS ya queda habilitado para:

```text
http://localhost:*
http://127.0.0.1:*
http://192.168.*.*:*
http://10.*.*.*:*
http://172.*.*.*:*
```

## Donde conecta el frontend React actual

El punto central esta en:

```text
frontend/src/app/api.ts
```

Ahi se define `API_BASE_URL`. En desarrollo Vite usa `/api/v1`; en build productivo usa el mismo host del navegador apuntando al puerto `8080`:

```text
http://HOST_DEL_FRONT:8080/api/v1
```

Los formularios de publicacion y solicitud envian ubicacion y disponibilidad con opciones fijas desde:

```text
LOCATION_OPTIONS
WEEK_DAYS
TIME_OPTIONS
```

Eso simplifica el matching porque el backend recibe siempre `localidad`, `diaSemana`, `horaDesde` y `horaHasta` en formato consistente.

## Como levantar el backend

Comando esperado:

```powershell
.\mvnw.cmd spring-boot:run
```

En esta maquina el wrapper de PowerShell habia fallado. Si vuelve a pasar, usar el Maven que ya bajo el wrapper:

```powershell
& 'C:\Users\juanc\.m2\wrapper\dists\apache-maven-3.9.14-bin\1cb7fhup6b5n3bed6kckbrnspv\apache-maven-3.9.14\bin\mvn.cmd' spring-boot:run
```

El backend ya usa los adapters JPA conectados a PostgreSQL. Para correr local, levantar la base con Docker o configurar estas variables:

```text
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/Servify
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

Los tests usan H2 solo como base liviana de prueba, pero pasan por los adapters JPA reales.

## Convenciones JSON

Enviar siempre:

```http
Content-Type: application/json
```

Enums esperados:

```text
Rol: ADMIN, USUARIO
ModalidadServicio: PRESENCIAL, VIRTUAL, MIXTA
EstadoCategoria: ACTIVA, INACTIVA
EstadoPublicacion: ACTIVA, INACTIVA, PAUSADA, BLOQUEADA, ELIMINADA
TipoRespuestaDistribucion: ACEPTAR, RECHAZAR
TipoDecisionSolicitud: ACEPTAR, RECHAZAR
RolConfirmante: SOLICITANTE, PRESTADOR
DayOfWeek: MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
```

Formato de hora:

```json
"09:00:00"
```

## Flujo MVP Para React

### 1. Crear usuario

Pantalla sugerida: registro.

```http
POST /usuarios
```

Body:

```json
{
  "email": "cliente@servify.test",
  "telefono": "1111",
  "rol": "USUARIO"
}
```

React debe guardar:

```text
response.id -> usuarioId
```

Consulta rapida para verificar backend local:

```http
GET /usuarios?estado=ACTIVO
```

Si no hay usuarios creados en la ejecucion actual, responde `[]`. Con PostgreSQL, los datos persisten mientras el volumen de Docker/base real se mantenga.

### 2. Registrar credenciales

Pantalla sugerida: registro / set password.

```http
POST /auth/credenciales
```

Body:

```json
{
  "usuarioId": "UUID_DEL_USUARIO",
  "emailAcceso": "cliente@servify.test",
  "passwordPlano": "123456"
}
```

Respuesta: `201 Created` sin body.

### 3. Login

Pantalla sugerida: login.

```http
POST /auth/login
```

Body:

```json
{
  "emailAcceso": "cliente@servify.test",
  "passwordPlano": "123456"
}
```

React debe guardar:

```text
response.usuarioId
response.accessToken.token
response.refreshToken.token
```

Nota: el MVP emite tokens, pero todavia no hay filtro de seguridad aplicado a los endpoints.

### 3.b Login social con Google o LinkedIn

Pantalla sugerida: login / registro social desde Expo.

El frontend hace el login con Google o LinkedIn, obtiene un `idToken` OIDC y lo manda al backend. El backend valida firma, issuer, audience/client-id, expiracion, `sub`, `email` y `email_verified`; luego crea o vincula el usuario y devuelve la misma forma de `SesionResult` que el login por password.

Google:

```http
POST /auth/social/google
```

LinkedIn:

```http
POST /auth/social/linkedin
```

Body:

```json
{
  "idToken": "ID_TOKEN_DEL_PROVEEDOR",
  "nonce": "NONCE_USADO_EN_EL_LOGIN",
  "rol": "USUARIO",
  "telefono": "1111"
}
```

`nonce`, `rol` y `telefono` son opcionales. Para usuarios nuevos, el backend usa `USUARIO` por defecto. No permite crear `ADMIN` desde login social.

React/Expo debe guardar lo mismo que en login por password:

```text
response.usuarioId
response.emailAcceso
response.accessToken.token
response.refreshToken.token
```

Config necesaria en backend:

```properties
servify.auth.external.google.client-ids=${SERVIFY_GOOGLE_CLIENT_IDS:}
servify.auth.external.linkedin.client-ids=${SERVIFY_LINKEDIN_CLIENT_IDS:}
```

En PowerShell local:

```powershell
$env:SERVIFY_GOOGLE_CLIENT_IDS="CLIENT_ID_GOOGLE_EXPO_O_WEB"
$env:SERVIFY_LINKEDIN_CLIENT_IDS="CLIENT_ID_LINKEDIN"
```

Guia completa: `docs/autenticacion-social-google-linkedin-servify.md`.

### 4. Completar perfil de prestador

Pantalla sugerida: onboarding de prestador.

```http
PUT /usuarios/{usuarioId}/perfil
```

Body:

```json
{
  "nombre": "Ana",
  "apellido": "Gomez",
  "edad": 32,
  "fotoPerfilUrl": "https://cdn.servify.test/ana.jpg",
  "ubicacion": {
    "pais": "Argentina",
    "provincia": "Buenos Aires",
    "ciudad": "CABA",
    "localidad": "Palermo",
    "calle": "Santa Fe",
    "altura": "1234",
    "referencia": "PB",
    "latitud": -34.58,
    "longitud": -58.42
  },
  "descripcionPersonal": "Plomeria general"
}
```

React puede usar:

```http
GET /usuarios/{usuarioId}/perfil
GET /usuarios/{usuarioId}/cuenta
GET /usuarios/{usuarioId}/reputacion
```

`/reputacion` devuelve:

```json
{
  "usuarioId": "UUID_USUARIO",
  "cantidadValoraciones": 2,
  "promedioEstrellas": 4.5
}
```

### 5. Categorias

Pantalla sugerida: seed/admin simple o panel interno del prototipo.

Crear categoria:

```http
POST /categorias
```

Body:

```json
{
  "nombre": "Plomeria",
  "descripcion": "Arreglos domesticos"
}
```

Activar categoria:

```http
PATCH /categorias/{categoriaId}/estado
```

Body:

```json
{
  "estadoDestino": "ACTIVA",
  "motivo": "Preparacion MVP"
}
```

Listar categorias activas:

```http
GET /categorias/activas
```

React debe usar `categoria.id` para crear publicaciones y solicitudes.

### 6. Crear publicacion del prestador

Pantalla sugerida: alta de servicio ofrecido.

```http
POST /publicaciones
```

Body:

```json
{
  "usuarioId": "UUID_PRESTADOR",
  "categoriaServicioId": "UUID_CATEGORIA",
  "titulo": "Plomera a domicilio",
  "descripcion": "Reparaciones y mantenimiento",
  "modalidadServicio": "PRESENCIAL",
  "ubicacion": {
    "pais": "Argentina",
    "provincia": "Buenos Aires",
    "ciudad": "CABA",
    "localidad": "Palermo",
    "calle": "Santa Fe",
    "altura": "1234",
    "referencia": "PB",
    "latitud": -34.58,
    "longitud": -58.42
  },
  "disponibilidadesHorarias": [
    {
      "diaSemana": "MONDAY",
      "horaDesde": "09:00:00",
      "horaHasta": "12:00:00"
    }
  ],
  "precioBase": 1000
}
```

React debe guardar:

```text
response.id -> publicacionId
```

Activar publicacion:

```http
PATCH /publicaciones/{publicacionId}/estado
```

Body:

```json
{
  "usuarioId": "UUID_PRESTADOR",
  "estadoDestino": "ACTIVA",
  "motivo": "Lista para recibir solicitudes"
}
```

Consultas utiles:

```http
GET /publicaciones/{publicacionId}
GET /usuarios/{usuarioId}/publicaciones
GET /categorias/{categoriaId}/publicaciones
```

`GET /categorias/{categoriaId}/publicaciones` devuelve las publicaciones activas de esa categoria. El front lo usa cuando el usuario entra desde una categoria del inicio; si responde `[]`, se muestra el estado vacio.

### 7. Crear solicitud del cliente

Pantalla sugerida: formulario de pedido de servicio.

```http
POST /solicitudes
```

Body:

```json
{
  "solicitanteId": "UUID_CLIENTE",
  "categoriaServicioId": "UUID_CATEGORIA",
  "modalidadServicio": "PRESENCIAL",
  "ubicacion": {
    "pais": "Argentina",
    "provincia": "Buenos Aires",
    "ciudad": "CABA",
    "localidad": "Palermo",
    "calle": "Santa Fe",
    "altura": "1400",
    "referencia": "Depto 2",
    "latitud": -34.58,
    "longitud": -58.42
  },
  "disponibilidadRequerida": {
    "diaSemana": "MONDAY",
    "horaDesde": "10:00:00",
    "horaHasta": "11:00:00"
  },
  "descripcionNecesidad": "Necesito reparar una perdida",
  "precioReferencia": 1500
}
```

React debe guardar:

```text
response.id -> solicitudId
```

Al crear la solicitud, el backend dispara la distribucion inicial a publicaciones compatibles.

Consultas utiles:

```http
GET /solicitudes/{solicitudId}
GET /usuarios/{solicitanteId}/solicitudes
GET /solicitudes/{solicitudId}/estado-asignacion
```

### 8. Bandeja del prestador

Pantalla sugerida: solicitudes recibidas.

```http
GET /prestadores/{prestadorId}/solicitudes-recibidas
```

React debe guardar de cada item:

```text
item.distribucionSolicitudId -> requerido para aceptar, rechazar o contraofertar
item.solicitudId
item.publicacionServicioId
```

### 9. Responder una distribucion

Pantalla sugerida: detalle de solicitud recibida.

Aceptar o rechazar:

```http
POST /distribuciones/{distribucionSolicitudId}/respuestas
```

Body:

```json
{
  "prestadorId": "UUID_PRESTADOR",
  "tipoRespuesta": "ACEPTAR"
}
```

Respuesta: `204 No Content`.

### 10. Contraoferta

Pantalla sugerida: detalle de solicitud recibida, opcion "proponer otro precio".

Emitir contraoferta:

```http
POST /distribuciones/{distribucionSolicitudId}/contraofertas
```

Body:

```json
{
  "prestadorId": "UUID_PRESTADOR",
  "precioPropuesto": 1800,
  "mensaje": "Incluye materiales"
}
```

Respuesta: `202 Accepted`.

El cliente obtiene la contraoferta pendiente desde:

```http
GET /solicitudes/{solicitudId}/estado-asignacion
```

React debe leer:

```text
response.contraofertasPendientes[].id -> contraofertaId
```

Resolver contraoferta:

```http
POST /contraofertas/{contraofertaId}/resoluciones
```

Body:

```json
{
  "solicitanteId": "UUID_CLIENTE",
  "decision": "ACEPTAR"
}
```

Respuesta: `ContraofertaResult`.

### 11. Confirmar asignacion

Pantalla sugerida: cliente elige prestador aceptado o contraoferta aceptada.

```http
POST /solicitudes/{solicitudId}/asignaciones/confirmaciones
```

Body:

```json
{
  "distribucionSolicitudId": "UUID_DISTRIBUCION",
  "solicitanteId": "UUID_CLIENTE"
}
```

React debe guardar:

```text
response.id -> asignacionServicioId
response.prestadorId
```

### 12. Confirmar finalizacion

Pantalla sugerida: cierre de trabajo para cliente y prestador.

Debe llamarse una vez por cada parte.

```http
POST /solicitudes/{solicitudId}/finalizaciones/confirmaciones
```

Body cliente:

```json
{
  "asignacionServicioId": "UUID_ASIGNACION",
  "confirmanteId": "UUID_CLIENTE",
  "rolConfirmante": "SOLICITANTE",
  "observacion": "Trabajo recibido"
}
```

Body prestador:

```json
{
  "asignacionServicioId": "UUID_ASIGNACION",
  "confirmanteId": "UUID_PRESTADOR",
  "rolConfirmante": "PRESTADOR",
  "observacion": "Trabajo realizado"
}
```

Cuando estan ambas confirmaciones, la solicitud pasa a `FINALIZADA`.

### 13. Calificar servicio

Pantalla sugerida: calificacion posterior a finalizacion.

```http
POST /solicitudes/{solicitudId}/calificaciones
```

Body:

```json
{
  "asignacionServicioId": "UUID_ASIGNACION",
  "solicitanteId": "UUID_CLIENTE",
  "prestadorId": "UUID_PRESTADOR",
  "puntaje": 5
}
```

Respuesta: `201 Created` sin body.

### 14. Cancelar solicitud

Pantalla sugerida: detalle de solicitud del cliente.

```http
DELETE /solicitudes/{solicitudId}
```

Body:

```json
{
  "solicitanteId": "UUID_CLIENTE"
}
```

Respuesta: `204 No Content`.

## Endpoints administrativos MVP

Configuracion vigente:

```http
GET /admin/configuracion
```

Moderacion de publicacion:

```http
PATCH /admin/publicaciones/{publicacionId}/moderacion
```

Body:

```json
{
  "administradorId": "UUID_ADMIN",
  "estadoDestino": "BLOQUEADA",
  "motivo": "Revision manual"
}
```

## Tests HTTP agregados

La conexion REST del flujo principal queda cubierta por:

```text
src/test/java/com/servify/MvpHttpFlowTests.java
```

Ese test valida que el front pueda obtener los IDs necesarios:

```text
usuarioId
categoriaId
publicacionId
solicitudId
distribucionSolicitudId
asignacionServicioId
```

Resultado verificado:

```text
Tests run: 13, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```
