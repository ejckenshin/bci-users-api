# bci-users-api
Este API tiene como principal objetivo la creación de un usuario, asi como otras operaciones para dicha gestión, en esta primera versión v1, sólo se tendrá la creación y el obtener usuario.
## Tecnologías
- **Java 8**
- **Spring Boot 2.3.1.RELEASE**
- **Spring Data JPA**
- **Mapstruct**
- **Lombok**
- **OpenApi**
- **Jakarta Validation**
- **JUnit 5 & Mockito**
- **H2 Database**
- **Maven**

## Cómo Empezar
### Requisitos
- Java 1.8
- Apache maven
- Git

### Instalación
1. Clonar el repositorio:
`git clone https://github.com/ejckenshin/bci-users-api.git`

2. Navegar en el directorio del proyecto:
`cd bci-users-api`

### Compilar y ejecutar
- Compilación: `mvn clean install`
- Ejecutar la aplicación: `mvn spring-boot:run`
- La API se ejecutará en: `http://localhost:8080/bci-users-api/v1`

## Postman Collection

Puedes descargar nuestra colección de Postman para probar los endpoints de la API.

[Descargar Postman Collection](https://raw.githubusercontent.com/ejckenshin/bci-users-api/refs/heads/master/bci-users-api.postman_collection.json)

## Endpoints de la API

Este proyecto usa openapi, y podemos encontrar también la documentación:
- Interfaz de usuario de Swagger: `http://localhost:8080/bci-users-api/v1/swagger-ui/index.html`
- Documentación en formato JSON/YAML: `http://localhost:8080/bci-users-api/v1/v3/api-docs`

| HTTP Method | Endpoint      | Descripción                  |
|-------------|---------------|------------------------------|
| POST        | `/users`      | Crea un nuevo usuario.       |

## Base de Datos
- Este proyecto utiliza la base de datos en memoria H2.
- La consola H2 se puede acceder en: `http://localhost:8080/bci-users-api/v1/h2-console`
    - **JDBC URL**: `jdbc:h2:mem:bci`
    - **Username**: sa
    - **Password**: (dejar en blanco)
- Los scripts relacionados a este API se encuentra en: `src/main/resources/scripts`