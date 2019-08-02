#!/bin/bash

# Docker-Image mit Fat-Jar erzeugen.
#
# Nach Ausführen dieses Skripts sollte Image in der mit folgendem
# Befehl ausgegebenen Liste auftauchen: docker image list

docker build --tag mdecker/restapi-demo:0.0.1 .
# --tag: <repository>:<tag>

