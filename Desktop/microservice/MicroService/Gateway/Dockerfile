# Use Java 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar gateway.jar

# Expose the gateway port
EXPOSE 8093

# Run the application
ENTRYPOINT ["java", "-jar", "gateway.jar"]
