# Diese Datei enthält die Beschreibung für "docker build", um ein
# Image mit der REST-API-Demo-App zu erstellen.
#
# Erzeugen des Image: docker_build.sh
#
# Siehe auch:
#   https://spring.io/guides/gs/spring-boot-docker/
#   https://www.baeldung.com/dockerizing-spring-boot-application

# Base image: "Alpine Linux" & OpenJKD
FROM openjdk:8-jdk-alpine

LABEL maintainer="MDecker-MobileComputing"

# Dockerfile selbst in Image kopieren  
COPY ./Dockerfile .

# Fat Jar mit SpringBoot-Applikation
COPY ./target/restapidemo-0.0.1-SNAPSHOT.jar .

# Start der SpringBoot-App
#CMD java -jar restapidemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","restapidemo-0.0.1-SNAPSHOT.jar"]

# Port nach aussen freigeben
EXPOSE 8080

