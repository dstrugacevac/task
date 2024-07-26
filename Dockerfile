FROM maven:3.8.7-eclipse-temurin-17-focal AS build

WORKDIR /app

# Copy the pom.xml file
COPY pom.xml /app/

# Run Maven build to download dependencies and cache them
RUN mvn dependency:go-offline

# Copy the source code
COPY src /app/src

# Run Maven build with Testcontainers and PostgreSQL
RUN export DATABASE_URL=jdbc:postgresql://localhost:5432/product-task \
    && export DATABASE_USERNAME=postgres \
    && export DATABASE_PASSWORD=password \
    && mvn clean install -Dprofile=local

# Stage 2: Create a smaller image without Maven
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/task-0.0.1-SNAPSHOTjar /app/app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
