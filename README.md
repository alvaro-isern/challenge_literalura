# Challenge Literalura

## Descripción

Challenge Literalura es una aplicación Java basada en Spring Boot que permite gestionar libros y autores. La aplicación se conecta a la API https://gutendex.com/books para buscar libros y almacena la información en una base de datos PostgreSQL.

## Características

- Buscar libros por título utilizando una API externa.
- Listar todos los libros registrados.
- Listar todos los autores registrados.
- Listar autores vivos por un determinado año.
- Listar libros por idioma.

## Requisitos

- Java 17 o superior
- Maven 3.6.0 o superior
- PostgreSQL

## Configuración

1. Clona el repositorio:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    cd challenge_literalura
    ```

2. Configura las variables de entorno para la base de datos en el archivo `application.properties`:
    ```ini
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver
    hibernate.dialect=org.hibernate.dialect.HSQLDialect

    spring.jpa.hibernate.ddl-auto=update

    spring.jpa.show-sql=false
    spring.jpa.format-sql = false
    ```

3. Construye el proyecto con Maven:
    ```sh
    mvn clean install
    ```

## Ejecución

Para ejecutar la aplicación, usa el siguiente comando:
```sh
mvn spring-boot:run
