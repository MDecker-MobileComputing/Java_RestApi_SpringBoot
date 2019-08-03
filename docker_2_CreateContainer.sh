#!/bin/bash

# Skript erzeugt einen Container vom Image, startet den Container aber nicht gleich.
# Mit "docker run -p ..." kann man einen Container erzeugen und gleich starten.

docker create --rm --name=restapidemo_container -p 8080:8080 mdecker/restapi-demo:0.0.1
# -p: <port_host>:<port_container>
# Wenn kein Name mit --name spezifiziert wird, dann erfindet Docker einen Namen
# nach dem Muster <Adjektiv>_<Substantiv>, z.B. "dreany_cerf".
# Es darf noch keinen lokalen Container mit diesem Namen geben.
# -rm: Container entfernen, wenn es ihn schon gibt.

echo -e "\nListe der lokalen Container:"
docker container list --all
# --all: auch nicht laufende Container anzeigen
# alter Befehl: docker ps --all

echo