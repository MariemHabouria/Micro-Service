# Use Java 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar gestionabonnement.jar

# Expose port
EXPOSE 8084

# Run the JAR
ENTRYPOINT ["java", "-jar", "gestionabonnement.jar"]
