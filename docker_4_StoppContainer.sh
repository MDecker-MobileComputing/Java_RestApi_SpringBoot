#!/bin/bash

# Container anhalten.

docker stop restapidemo_container

echo -e "\nListe der lokal laufenden Container:\n"

docker container list
# alter Befehl: docker ps
# ohne Option "-all", so dass nur laufende Container angezeigt werden.

# Um einen laufenden Container neu zu starten:
# docker restart restapidemo_container

