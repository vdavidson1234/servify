# Autenticacion social Google y LinkedIn - Servify

Fecha: 28 de mayo de 2026

## Objetivo

Dejar listo el backend del MVP para que el frontend React/Expo pueda iniciar sesion con Google o LinkedIn sin manejar passwords propias de esos proveedores.

El frontend obtiene un `idToken` OIDC desde Google o LinkedIn y se lo envia al backend. El backend no confia ciegamente en ese token: valida firma, issuer, audience/client-id, expiracion, subject y email verificado. Si todo es valido, crea o vincula un usuario Servify y emite los tokens propios de Servify.

Documentacion oficial consultada:

- Google OpenID Connect: https://developers.google.com/identity/openid-connect/openid-connect
- Google OAuth 2.0 Web Server Flow: https://developers.google.com/identity/protocols/oauth2/web-server
- LinkedIn OpenID Connect: https://learn.microsoft.com/en-us/linkedin/consumer/integrations/self-serve/sign-in-with-linkedin-v2
- Spring Security JWT/OIDC: https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html

## Que problema resuelve

Antes, el MVP tenia solamente:

```http
POST /api/v1/auth/credenciales
POST /api/v1/auth/login
POST /api/v1/auth/refresh
POST /api/v1/auth/logout
```

Eso sirve para usuario y password propios de Servify.

Ahora tambien existe:

```http
POST /api/v1/auth/social/google
POST /api/v1/auth/social/linkedin
```

Estos endpoints permiten login o registro social usando OpenID Connect.

## Conceptos clave

`OAuth 2.0`

Protocolo de autorizacion. Sirve para que una app obtenga permisos o tokens de un proveedor externo.

`OpenID Connect` u `OIDC`

Capa de identidad sobre OAuth 2.0. Agrega el `idToken`, que es un JWT firmado por el proveedor y contiene identidad del usuario.

`idToken`

JWT emitido por Google o LinkedIn. Dice, de forma firmada, datos como:

```text
iss -> quien emitio el token
aud -> para que client-id fue emitido
sub -> identificador estable del usuario dentro del proveedor
exp -> vencimiento
email -> email del usuario
email_verified -> si el proveedor verifico ese email
```

`sub`

Es mas importante que el email. El email puede cambiar; el `sub` identifica la cuenta del proveedor de manera estable. Por eso Servify guarda `proveedor + sub`.

`JWKS`

Conjunto de claves publicas del proveedor. El backend las usa para verificar que el `idToken` fue firmado realmente por Google o LinkedIn.

`nonce`

Valor aleatorio opcional que el frontend puede mandar al proveedor y luego al backend. Si se envia, el backend exige que el token tenga el mismo `nonce`. Ayuda contra replay de tokens.

## Flujo implementado

```mermaid
sequenceDiagram
    participant App as React/Expo
    participant Provider as Google o LinkedIn
    participant API as Servify Backend
    participant Store as DB o memoria MVP

    App->>Provider: Login OIDC con scope openid profile email
    Provider-->>App: idToken
    App->>API: POST /api/v1/auth/social/{proveedor}
    API->>Provider: Obtiene claves JWKS si necesita validar firma
    API->>API: Valida iss, aud, exp, firma, sub, email_verified
    API->>Store: Busca identidad externa por proveedor + sub
    alt Identidad existente
        API->>Store: Actualiza ultimo acceso
    else Identidad nueva
        API->>Store: Busca usuario por email verificado
        alt Usuario existente
            API->>Store: Vincula identidad externa al usuario
        else Usuario nuevo
            API->>Store: Crea usuario ACTIVO rol USUARIO
            API->>Store: Guarda identidad externa
        end
    end
    API-->>App: SesionResult con accessToken y refreshToken Servify
```

## Endpoint para el frontend

Base local:

```text
http://localhost:8080/api/v1
```

Google:

```http
POST /auth/social/google
```

LinkedIn:

```http
POST /auth/social/linkedin
```

Body minimo:

```json
{
  "idToken": "ID_TOKEN_DEL_PROVEEDOR"
}
```

Body completo:

```json
{
  "idToken": "ID_TOKEN_DEL_PROVEEDOR",
  "nonce": "NONCE_USADO_EN_EL_LOGIN",
  "rol": "USUARIO",
  "telefono": "1111"
}
```

Respuesta:

```json
{
  "usuarioId": "uuid",
  "emailAcceso": "usuario@email.com",
  "accessToken": {
    "token": "access-...",
    "tipoToken": "Bearer",
    "fechaEmision": "...",
    "fechaExpiracion": "..."
  },
  "refreshToken": {
    "token": "refresh-...",
    "tipoToken": "Bearer",
    "fechaEmision": "...",
    "fechaExpiracion": "..."
  },
  "fechaInicioSesion": "..."
}
```

El frontend debe guardar:

```text
usuarioId
emailAcceso
accessToken.token
refreshToken.token
```

Para renovar sesion se usa el endpoint ya existente:

```http
POST /auth/refresh
```

Body:

```json
{
  "refreshToken": "REFRESH_TOKEN_SERVIFY"
}
```

## Configuracion backend

Archivo:

```text
src/main/resources/application.properties
```

Propiedades agregadas:

```properties
servify.auth.external.google.client-ids=${SERVIFY_GOOGLE_CLIENT_IDS:}
servify.auth.external.linkedin.client-ids=${SERVIFY_LINKEDIN_CLIENT_IDS:}
```

En local real se recomienda cargarlas como variables de entorno para no commitear IDs de cada ambiente:

```powershell
$env:SERVIFY_GOOGLE_CLIENT_IDS="1234567890-xxxx.apps.googleusercontent.com"
$env:SERVIFY_LINKEDIN_CLIENT_IDS="77abc123xyz"
```

Tambien pueden completarse directo en `application.properties`, pero es menos flexible:

```properties
servify.auth.external.google.client-ids=1234567890-xxxx.apps.googleusercontent.com
servify.auth.external.linkedin.client-ids=77abc123xyz
```

Si hay mas de un client-id valido, por ejemplo Expo Go, Android standalone y web, se pueden separar por coma:

```properties
servify.auth.external.google.client-ids=CLIENT_ID_EXPO,CLIENT_ID_ANDROID,CLIENT_ID_WEB
```

Defaults usados por el backend:

```text
Google issuer aceptado:
https://accounts.google.com
accounts.google.com

Google JWKS:
https://www.googleapis.com/oauth2/v3/certs

LinkedIn issuer aceptado:
https://www.linkedin.com

LinkedIn JWKS:
https://www.linkedin.com/oauth/openid/jwks
```

## Configuracion Google

En Google Cloud Console:

1. Crear o elegir proyecto.
2. Configurar OAuth consent screen.
3. Crear OAuth Client ID.
4. Para Expo/React Native, usar el client-id que corresponda al flujo elegido.
5. Pedir scopes:

```text
openid profile email
```

6. Enviar al backend el `idToken` obtenido por el frontend.

El backend valida:

```text
firma contra JWKS
iss = https://accounts.google.com o accounts.google.com
aud = uno de servify.auth.external.google.client-ids
exp no vencido
sub presente
email presente
email_verified = true
nonce, si el frontend lo envia
```

## Configuracion LinkedIn

En LinkedIn Developer Portal:

1. Crear o elegir app.
2. Habilitar Sign In with LinkedIn using OpenID Connect.
3. Configurar redirect URI segun Expo/React Native.
4. Pedir scopes:

```text
openid profile email
```

5. Enviar al backend el `idToken` obtenido por el frontend.

El backend valida:

```text
firma contra JWKS
iss = https://www.linkedin.com
aud = uno de servify.auth.external.linkedin.client-ids
exp no vencido
sub presente
email presente
email_verified = true
nonce, si el frontend lo envia
```

Nota importante: LinkedIn marca `email` y `email_verified` como campos opcionales en algunos contextos. En Servify se exige `email_verified = true` para crear o vincular cuenta. Si LinkedIn no devuelve ese dato, el login se rechaza para evitar vincular una cuenta con email no comprobado.

## Como decide si registra o inicia sesion

1. El backend valida el `idToken`.
2. Busca `IdentidadExterna` por:

```text
proveedor + subject/sub
```

3. Si existe y esta habilitada:

```text
usa el usuario vinculado
actualiza ultimo acceso
emite tokens Servify
```

4. Si no existe:

```text
busca Usuario por email verificado
```

5. Si encuentra usuario:

```text
vincula esa identidad externa a ese usuario
```

6. Si no encuentra usuario:

```text
crea Usuario ACTIVO con rol USUARIO
crea IdentidadExterna
```

7. Si el usuario esta suspendido, bloqueado o inactivo:

```text
rechaza la autenticacion
```

## Que se guarda

Nuevo modelo:

```text
IdentidadExterna
```

Campos principales:

```text
id
usuarioId
proveedor: GOOGLE o LINKEDIN
subject: sub del proveedor
email
emailVerificado
nombreMostrado
fechaVinculacion
ultimoAcceso
habilitada
```

Tambien se extendio `RefreshToken` para poder asociarlo con:

```text
credencialAccesoId -> login local por password
identidadExternaId -> login social
```

Esto permite que `/auth/refresh` funcione para ambos tipos de login.

## Que NO hace todavia

El MVP sigue emitiendo tokens Servify simples desde `TokenProviderPort`. Todavia no hay filtro de seguridad global que proteja endpoints con:

```http
Authorization: Bearer ACCESS_TOKEN_SERVIFY
```

Por ahora, los endpoints del MVP siguen recibiendo `usuarioId` en el body o path. El siguiente paso profesional seria convertir el access token Servify en JWT firmado propio y agregar Spring Security para autorizar endpoints por usuario/rol.

Tampoco se guardan access tokens de Google o LinkedIn. Esto es intencional: para el MVP solo necesitamos autenticar identidad, no llamar APIs externas en nombre del usuario.

## Archivos existentes modificados

```text
pom.xml
src/main/resources/application.properties
src/test/resources/application.properties
src/main/java/com/servify/autenticacion/domain/model/RefreshToken.java
src/main/java/com/servify/autenticacion/application/service/RenovarTokenService.java
src/main/java/com/servify/autenticacion/infrastructure/web/AuthApiController.java
src/main/java/com/servify/shared/infrastructure/config/MvpUseCaseConfiguration.java
src/main/java/com/servify/shared/infrastructure/memory/MvpInMemoryStore.java
src/main/java/com/servify/shared/infrastructure/memory/MvpInMemoryAdapterConfiguration.java
src/test/java/com/servify/MvpHttpFlowTests.java
docs/guia-api-react-mvp-servify.md
```

## Archivos nuevos

```text
src/main/java/com/servify/autenticacion/domain/enumtype/ProveedorIdentidadExterna.java
src/main/java/com/servify/autenticacion/domain/model/IdentidadExterna.java
src/main/java/com/servify/autenticacion/application/dto/AutenticarConIdentidadExternaCommand.java
src/main/java/com/servify/autenticacion/application/dto/IdentidadExternaVerificadaResult.java
src/main/java/com/servify/autenticacion/application/port/in/AutenticarConIdentidadExternaUseCase.java
src/main/java/com/servify/autenticacion/application/port/out/IdentidadExternaRepositoryPort.java
src/main/java/com/servify/autenticacion/application/port/out/ProveedorIdentidadVerifierPort.java
src/main/java/com/servify/autenticacion/application/service/AutenticarConIdentidadExternaService.java
src/main/java/com/servify/autenticacion/infrastructure/oauth/OidcProveedorProperties.java
src/main/java/com/servify/autenticacion/infrastructure/oauth/OidcIdentityVerifierConfiguration.java
src/main/java/com/servify/autenticacion/infrastructure/oauth/OidcJwtProveedorIdentidadVerifier.java
src/main/java/com/servify/autenticacion/infrastructure/oauth/FakeProveedorIdentidadVerifier.java
docs/autenticacion-social-google-linkedin-servify.md
```

## Tests agregados

Archivo:

```text
src/test/java/com/servify/MvpHttpFlowTests.java
```

Tests nuevos:

```text
autenticacionSocialGoogle_creaSesionYPermiteRenovarRefreshToken
autenticacionSocialLinkedin_vinculaUsuarioExistentePorEmailVerificado
```

Que validan:

```text
POST /api/v1/auth/social/google devuelve SesionResult
POST /api/v1/auth/social/linkedin vincula un usuario existente por email verificado
POST /api/v1/auth/refresh tambien funciona con refresh tokens de login social
```

Para tests se activo:

```properties
servify.auth.external.fake-verifier.enabled=true
```

Ese verificador fake solo se usa en tests. Acepta tokens con formato:

```text
fake:{proveedor}:{subject}:{email}
```

Ejemplo:

```text
fake:google:google-sub-http:social-http@servify.test
```

## Como ejecutar la suite

Wrapper esperado:

```powershell
.\mvnw.cmd test
```

Si el wrapper de PowerShell vuelve a fallar en esta maquina, usar Maven del wrapper:

```powershell
& 'C:\Users\juanc\.m2\wrapper\dists\apache-maven-3.9.14-bin\1cb7fhup6b5n3bed6kckbrnspv\apache-maven-3.9.14\bin\mvn.cmd' test
```

Resultado verificado:

```text
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Recomendaciones para la base de datos

Cuando se reemplacen los adapters en memoria por JPA, agregar una tabla para identidades externas.

Tabla sugerida:

```sql
CREATE TABLE identidades_externas (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    proveedor VARCHAR(30) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    email_verificado BOOLEAN NOT NULL,
    nombre_mostrado VARCHAR(255),
    fecha_vinculacion TIMESTAMP NOT NULL,
    ultimo_acceso TIMESTAMP,
    habilitada BOOLEAN NOT NULL,
    fecha_creacion TIMESTAMP,
    fecha_modificacion TIMESTAMP,
    CONSTRAINT uq_identidad_proveedor_subject UNIQUE (proveedor, subject),
    CONSTRAINT uq_identidad_usuario_proveedor UNIQUE (usuario_id, proveedor)
);
```

Actualizar `refresh_tokens` para permitir:

```text
credencial_acceso_id nullable
identidad_externa_id nullable
```

Regla recomendada:

```text
exactamente uno entre credencial_acceso_id e identidad_externa_id debe estar presente
```

## Checklist para conectar Expo

1. Obtener `idToken` desde Google o LinkedIn con scopes `openid profile email`.
2. Generar y guardar `nonce` si la libreria lo permite.
3. Enviar `idToken` y `nonce` a `/api/v1/auth/social/google` o `/api/v1/auth/social/linkedin`.
4. Guardar `usuarioId`, `accessToken.token` y `refreshToken.token`.
5. Usar `/api/v1/auth/refresh` cuando venza el access token.
6. No guardar client secret en Expo.
7. No mandar passwords de Google o LinkedIn al backend.
8. En produccion, usar HTTPS siempre.
