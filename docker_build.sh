#!/bin/bash

# Docker-Image mit Fat-Jar erzeugen, verwendet Datei "Dockerfile".
#
# Nach Ausführen dieses Skripts sollte Image in der mit folgendem
# Befehl ausgegebenen Liste auftauchen: docker image list
# Mit der "IMAGE ID" aus dieser Liste kann der Container dann
# erzeugt werden: docker run --detach <IMAGE_ID>.
# Start anhand Tag: docker run --detach mdecker/restapi-demo:0.0.1
#
# Container starten: docker start <IMAGE_ID>
# Container stoppen: docker stop  <IMAGE_ID>
#
# Shell zum Verbinden mit laufenden Container: docker exec -it <IMAGE_ID> sh

docker build --tag mdecker/restapi-demo:0.0.1 .
# --tag: <repository>:<tag>
#        Befehl kann mehrfach mit selben Tag ausgeführt werden

