# Servify

Proyecto organizado como monorepo simple:

- Backend Spring Boot en la raiz del repositorio.
- Base de datos PostgreSQL con `docker-compose.yml` e `init.sql`.
- Frontend React/Vite en `frontend/`.

Esta estructura evita mezclar `node_modules` con el backend y deja cada parte con sus propios comandos.

## Levantar la base de datos

Desde esta carpeta:

```bash
docker compose up -d
```

La primera vez, Docker ejecuta `init.sql` y crea las tablas.

Conexion JDBC usada por el backend:

```text
URL:      jdbc:postgresql://localhost:5432/Servify
Usuario:  postgres
Password: postgres
```

## Levantar el backend

Desde esta carpeta:

```bash
./mvnw spring-boot:run
```

En Windows tambien podes usar:

```bash
mvnw.cmd spring-boot:run
```

La API queda en `http://localhost:8080/api/v1`.

## Levantar el frontend

Desde la raiz del repo:

```bash
npm install
npm run frontend:dev
```

La app queda en `http://localhost:5173`.

El frontend usa el proxy de Vite para enviar `/api` al backend en `http://localhost:8080`.
Si Windows dice que `npm` no existe, instalar Node.js LTS y volver a abrir la terminal.

Si el backend corre en otro puerto, crear `frontend/.env.local`:

```bash
VITE_SERVIFY_BACKEND_URL=http://localhost:8080
```

## Apagar la base

```bash
docker compose down
```

Para borrar tambien los datos:

```bash
docker compose down -v
```
