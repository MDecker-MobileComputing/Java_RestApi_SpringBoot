#!/bin/bash

# Container (aber nicht Image) löschen.

docker rm restapidemo_container

# Nach Ausführen dieses Skripts sollte mit "docker container list --all"
# der Container nicht mehr angezeigt werden.


