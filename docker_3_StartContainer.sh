#!/bin/bash

docker start restapidemo_container

echo -e "\nListe der lokal laufenden Container:\n"

docker container list
# alter Befehl: docker ps
# ohne Option "-all", so dass nur laufende Container angezeigt werden.


echo -e "\nLog-Output von Container-Start:\n" 
docker logs restapidemo_container

