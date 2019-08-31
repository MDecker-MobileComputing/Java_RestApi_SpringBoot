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

# RUN wird nur beim Erstellen des Images ausgeführt
RUN mkdir -p /home/restapidemo
# -p: Kein Fehler wenn Verzeichnis schon existiert

# Paket installieren (hier nur ein Texteditor, der bei Verbindungen mit "docker exec -it ..." verwendet werden kann)
# "apk" ist der Paket-Manager der Linux Distribution "Alpine Linux", auf der unser Image aufbaut.
RUN apk add joe

# Arbeitsverzeichnis für Befehle RUN, CMD, ENTRYPOINT, COPY und ADD
WORKDIR /home/restapidemo

# Dockerfile selbst in Image kopieren  
COPY ./Dockerfile .

# Fat Jar mit SpringBoot-Applikation
COPY ./target/restapidemo-0.0.1-SNAPSHOT.jar .

# Start der SpringBoot-App
# CMD & ENTRYPOINT werden beim Start des Containers ausgeführt
#CMD java -jar restapidemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","restapidemo-0.0.1-SNAPSHOT.jar"]

# Port nach aussen freigeben
EXPOSE 8080

