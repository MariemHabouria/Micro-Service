# Use Java 17 as base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar eurekaserver.jar

# Expose port for Eureka
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "eurekaserver.jar"]
