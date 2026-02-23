# Etapa 1: Build con Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src src

# Dar permisos de ejecución al Maven Wrapper
RUN chmod +x mvnw

# Compilar el proyecto
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final solo con JAR
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/appetize-0.0.1-SNAPSHOT.jar appetize.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appetize.jar"]
