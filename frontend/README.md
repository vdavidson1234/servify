
# Servify frontend

Frontend React/Vite limpio para el backend actual de Servify. Esta carpeta es el proyecto de frontend dentro del monorepo.

## Que se corrigio

- El ZIP original traia `node_modules`, por eso tenia mas de 65 mil elementos.
- Se copio solo el codigo fuente y assets necesarios.
- El `package.json` quedo reducido a las dependencias usadas por la app.
- Vite proxyea `/api` al backend Spring en `http://localhost:8080`.

## Levantar

1. Desde la raiz del repo `Servify`, levantar PostgreSQL:

```bash
docker compose up -d
```

2. Levantar Spring Boot en `http://localhost:8080`.

```bash
./mvnw spring-boot:run
```

3. Desde la raiz del repo `Servify`, instalar dependencias y correr Vite:

```bash
npm install
npm run frontend:dev
```

La app queda en `http://localhost:5173` y las llamadas a `/api/v1` van al backend actual.

Si Windows dice que `npm` no existe, instalar Node.js LTS y volver a abrir la terminal para que `npm` quede en el PATH.

Si el backend corre en otro host o puerto, crear `.env.local`:

```bash
VITE_SERVIFY_BACKEND_URL=http://localhost:8080
```

## Login con Google

La pantalla de login/registro carga Google Identity Services y, cuando Google devuelve un `idToken`, llama al endpoint del backend:

```bash
POST /api/v1/auth/social/google
```

Para probarlo en este frontend web/Vite, crear `.env.local` en `frontend` con un OAuth Client ID de tipo **Web application**:

```bash
VITE_GOOGLE_CLIENT_ID=TU_CLIENT_ID_WEB.apps.googleusercontent.com
```

En Google Cloud, ese cliente web debe tener como JavaScript origin autorizado:

```bash
http://localhost:5173
```

El Client ID de iOS sirve para Expo/iOS nativo. Para este frontend web conviene usar uno web, y ese mismo ID tambien debe estar permitido en el backend dentro de `SERVIFY_GOOGLE_CLIENT_IDS` o en `servify.auth.external.google.client-ids`.
  
