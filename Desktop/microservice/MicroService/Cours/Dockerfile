FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar gestioncours.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "gestioncours.jar"]
