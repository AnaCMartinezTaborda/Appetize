# Etapa 1: Build con Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final solo con JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/appetize-0.0.1-SNAPSHOT.jar appetize.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appetize.jar"]
