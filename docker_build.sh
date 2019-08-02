#!/bin/bash

# Docker-Image mit Fat-Jar erzeugen, verwendet Datei "Dockerfile".
#
# Nach Ausführen dieses Skripts sollte Image in der mit folgendem
# Befehl ausgegebenen Liste auftauchen: docker image list
# Diese Liste enthaelt u.a. die IMAGE ID
#
# Erzeugung Container anhand IMAGE ID: docker run --detach <IMAGE_ID>
# Start     Container anhand Tag     : docker run --detach mdecker/restapi-demo:0.0.1
#
# Container starten: docker start <CONTAINER_ID>
# Container stoppen: docker stop  <CONTAINER_ID>
#
# Liste der laufenden Container: docker ps
#
# Shell zum Verbinden mit laufenden Container: docker exec -it <IMAGE_ID> sh


docker build --tag mdecker/restapi-demo:0.0.1 .
# --tag: <repository>:<tag>
#        Befehl kann mehrfach mit selben Tag ausgeführt werden
# Punkt "." am Ende des Befehls steht für aktuelles Verzeichnis

