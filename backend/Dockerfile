FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/appetize-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} appetize.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appetize.jar"]