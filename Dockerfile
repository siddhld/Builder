FROM openjdk:17-jdk-slim AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

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
COPY --from=build /app/target/*.jar app.jar

# Entrypoint to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]