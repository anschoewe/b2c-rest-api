FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY build/libs/b2c-rest-api-0.0.1-SNAPSHOT.jar app.jar
COPY truststore.p12 truststore.p12
COPY keystore.p12 keystore.p12
EXPOSE 8901
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
