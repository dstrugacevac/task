# Product Management Application

## Overview

The Product Management Application is a service built using Java 17, PostgreSQL, and Spring Boot. It provides RESTful endpoints to manage products, including fetching individual products, listing all products, and creating new products.

## Prerequisites

Before you start, ensure you have the following installed:

- **Java 17**: Download and install Java 17 from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or via a package manager.
- **Docker**: Install Docker from [Docker's official website](https://www.docker.com/get-started).
- **Maven**: Install Maven from [Apache Maven's official website](https://maven.apache.org/install.html).

## Running the Application

### Using Docker

To run the application using Docker, follow these steps:

1. **Ensure Docker is running** on your machine.
2. **Navigate to the project directory** containing the `docker-compose.yml` file.
3. **Execute the following command**:

    ```sh
    docker-compose up
    ```

This command will build and start the application along with the PostgreSQL database.

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

For detailed API documentation, please refer to the Swagger UI [here](#). This UI provides interactive API documentation and testing capabilities.

## Additional Notes

- **Database Setup:** Ensure your PostgreSQL database is up and running before executing the Maven commands or starting Docker. The connection details are provided in the `mvn clean install` command.
- **Environment Variables:** Adjust the `DATASOURCE_JDBC_URL`, `DATASOURCE_USERNAME`, and `DATASOURCE_PASSWORD` as necessary to match your local or production database setup.


For further assistance, consult the [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) or reach out to the community.

---

