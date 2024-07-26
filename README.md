# Product Management Application

## Overview

The Product Management Application is a service built using Java 17, PostgreSQL, and Spring Boot. It provides RESTful endpoints to manage products, including fetching individual products, listing all products, and creating new products.

## Note

I know the application is usually run via Docker, and I have a script for that, but due to the generation of database data within the project, I simply need an external database just for the build. Therefore, you cannot run it directly through Docker because I don't have a hosted database.

## Prerequisites

Before you start, ensure you have the following installed:

- **Java 17**: Download and install Java 17 from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or via a package manager.
- **Maven**: Install Maven from [Apache Maven's official website](https://maven.apache.org/install.html).
- **PostgreSQL**: Install PostgreSQL from [PostgreSQL's official website](https://www.postgresql.org/download/).

## Setting Up the Database

1. **Start PostgreSQL** on your machine and log into the PostgreSQL command line interface:

    ```sh
    psql -U postgres
    ```

2. **Create the `product-task` database**:

    ```sql
    CREATE DATABASE "product-task";
    ```

3. **Create a user with necessary permissions**:

    ```sql
    CREATE USER taskUser WITH ENCRYPTED PASSWORD 'taskPassword';
    GRANT ALL PRIVILEGES ON DATABASE "product-task" TO taskUser;
    ```

## Running the Application

### Using Maven

To run the application using Maven, follow these steps:

1. **Clean and install the project dependencies**:

    ```sh
    mvn clean install -DDATASOURCE_JDBC_URL=jdbc:postgresql://localhost:5432/product-task -DDATASOURCE_USERNAME=taskUser -DDATASOURCE_PASSWORD=taskPassword -DskipTests -Dprofile=local
    ```

   This command sets up the database connection details and skips tests to speed up the build process.

2. **Run the application**:

    ```sh
    mvn spring-boot:run -Dprofile=local
    ```

   This command starts the Spring Boot application with the specified profile.

## API Documentation

For detailed API documentation, please refer to the Swagger UI [here](http://localhost:8080/swagger-ui/index.html). This UI provides interactive API documentation and testing capabilities.

## Additional Notes

- **Environment Variables:** Adjust the `DATASOURCE_JDBC_URL`, `DATASOURCE_USERNAME`, and `DATASOURCE_PASSWORD` as necessary to match your local or production database setup.

For further assistance, consult the [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) or reach out to the community.

---
