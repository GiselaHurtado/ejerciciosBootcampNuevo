# Catálogo de Películas 🎥🍿

Este proyecto es una aplicación que gestiona un catálogo de películas y actores. Está compuesta por un backend en Java (Spring Boot) que expone una API REST y un frontend en Angular que consume dicha API.

---

## Características ✨

- **Gestión de Actores 👥:**
  - Listado paginado de actores  
  - Consultar actor por ID  
  - Creación, actualización y eliminación de actores  
  - Consulta de películas en las que participa un actor

- **Gestión de Películas 🎬:**
  - Listado de películas (en versión corta y detallada)  
  - Creación, actualización y eliminación de películas  
  - Consulta de actores (reparto) y categorías de cada película  
  - Consulta de clasificaciones por edades  
  - Filtro de películas basado en título, duración, clasificación, etc.

---

## Tecnologías Utilizadas 🛠️

- **Backend:**
  - Java 17  
  - Spring Boot  
  - Spring Data JPA  
  - Hibernate  
  - MySQL

- **Frontend:**
  - Angular  
  - Bootstrap (para estilos y responsive)

- **Documentación de API:**
  - Swagger / OpenAPI

---

## Endpoints 📡

### Gestión de Actores 👥

- **GET /actores/v1**  
  Obtiene todos los actores paginados.

- **POST /actores/v1**  
  Crea un nuevo actor.

- **GET /actores/v1/{id}**  
  Obtiene un actor por su ID.

- **PUT /actores/v1/{id}**  
  Actualiza un actor existente.

- **DELETE /actores/v1/{id}**  
  Elimina un actor por su ID.

- **GET /actores/v1/{id}/pelis**  
  Lista las películas en las que participa el actor.

### Gestión de Películas 🎬

- **GET /peliculas/v1**  
  - Listado de las películas en versión corta (por defecto).  
  - Puede paginarse y obtenerse en formato corto o detallado según el parámetro `mode`.

- **POST /peliculas/v1**  
  Añade una nueva película.

- **GET /peliculas/v1/{id}**  
  Recupera una película.  
  *Se pueden obtener diferentes formatos:*  
  - Con `mode=short` → Devuelve un objeto corto (FilmShortDTO).  
  - Con `mode=details` → Devuelve un objeto con detalles (FilmDetailsDTO).  
  - Con `mode=edit` → Devuelve la película en formato editable (FilmEditDTO).

- **PUT /peliculas/v1/{id}**  
  Modifica una película existente. Los identificadores deben coincidir.

- **DELETE /peliculas/v1/{id}**  
  Borra una película existente.

- **GET /peliculas/v1/{id}/categorias**  
  Lista las categorías de la película.

- **GET /peliculas/v1/{id}/reparto**  
  Lista los actores (reparto) de la película.

- **GET /peliculas/v1/clasificaciones**  
  Lista las clasificaciones por edades disponibles.

- **GET /peliculas/v1/filtro**  
  Consulta filtrada de películas.  
  
  Parámetros de filtrado:  
  - `title`: que el título contenga un determinado valor  
  - `minlength` y `maxlength`: duración mínima y máxima  
  - `rating`: clasificación por edades (G, PG, PG-13, R, NC-17)  
  - `mode`: formato de salida (short o details)

---

## Schemas 📑

La API utiliza los siguientes schemas:

- **Actor**  
- **Category**  
- **FilmShortDTO:** Versión corta de la película (usada en listados).  
- **FilmDetailsDTO:** Versión detallada de la película.  
- **FilmEditDTO:** Versión editable para crear o modificar películas.  
- **ProblemDetail:** Estructura para manejo de errores.  
- **Titulo:** Utilizado para devolver el título de una película en algunos endpoints.

---

## Instalación y Ejecución 🚀

### Backend

1. Clona el repositorio y abre el proyecto en tu IDE favorito.
2. Configura la conexión a la base de datos (por ejemplo, en `application.properties` o `application.yml`).
3. Ejecuta la aplicación Spring Boot.
4. Accede a la documentación Swagger en `http://localhost:<PUERTO>/swagger-ui.html` para ver y probar la API.

### Frontend

1. Navega a la carpeta del proyecto Angular.
2. Instala las dependencias con:
   ```bash
   npm install
