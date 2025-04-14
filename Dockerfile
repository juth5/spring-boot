FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
