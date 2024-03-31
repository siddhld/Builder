# Multi-stage build for efficiency
FROM maven:3.8-jdk-17-slim AS build

# Copy project files
COPY . /app

# Set working directory
WORKDIR /app

# Build the JAR using Maven
RUN mvn clean package -DskipTests

# Final runtime stage
FROM openjdk:17-jdk-slim

# Expose port 8080
EXPOSE 8080

# Copy the JAR from the build stage
COPY --from=build /target/*.jar app.jar

# Entrypoint to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]