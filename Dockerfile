# Diese Datei enthält die Beschreibung für "docker build", um ein
# Image mit der REST-API-Demo-App zu erstellen.
#
# Befehl zum Erzeugen des Image: docker build -t RestApiMitSpringBoot .
#
# See also https://spring.io/guides/gs/spring-boot-docker/

# Base image: "Alpine Linux" & OpenJKD
FROM openjdk:8-jdk-alpine

LABEL maintainer="MDecker-MobileComputing"

# Dockerfile selbst in Image kopieren  
COPY ./Dockerfile .

# Fat Jar mit SpringBoot-Applikation
COPY ./target/restapidemo-0.0.1-SNAPSHOT.jar .

# Port nach aussen freigaben
EXPOSE 8080

# Start der SpringBoot-App
CMD java -jar restapidemo-0.0.1-SNAPSHOT.jar

