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
    git clone https://github.com/alvaro-isern/challenge_literalura.git
    ```

2. Configura las variables de entorno para la base de datos en el archivo `application.properties`:
    ```ini
    spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
    spring.datasource.username=${DB_USER}
    spring.datasource.password=${DB_PASSWORD}
    spring.datasource.driver-class-name=org.postgresql.Driver
    hibernate.dialect=org.hibernate.dialect.HSQLDialect

    spring.jpa.hibernate.ddl-auto=update

    spring.jpa.show-sql=false   // habilita con true para ver los procesos sql
    spring.jpa.format-sql = false // habilita con true para ver los procesos sql
    ```
    
## Uso

Una vez que la aplicación esté en ejecución, se mostrará un menú de opciones en la consola:

```console
=======================================================
*************** SELECCIONE UNA OPCIÓN *****************
=======================================================
    1) Buscar por Título de libro
    2) Listar todos los libros registrados
    3) Listar autores registrados
    4) Listar autores vivos por un determinado año
    5) Listar libros por idioma
    0) Salir
=======================================================
```

## Desarrolladores del Proyecto

- **Alvaro Isern** (Desarrollador Principal)  
  - [LinkedIn](www.linkedin.com/in/alvaro-isern-904808315)  
  - [GitHub](https://github.com/alvaro-isern)  
  - *Email:* <sss12345iiialvaro@gmail.com>

## Conclusión

Challenge Literalura es una aplicación robusta y eficiente para la gestión de libros y autores. Con su integración con una API externa y una base de datos PostgreSQL, proporciona una solución completa para buscar, registrar y listar libros y autores. La estructura modular del proyecto facilita su mantenimiento y escalabilidad. Esperamos que esta herramienta sea útil y agradecemos cualquier contribución para mejorarla.
