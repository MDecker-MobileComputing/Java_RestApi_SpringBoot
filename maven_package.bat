
mvn clean package

@REM Dito, aber Unit-Tests überspringen
@REM mvn -Dmaven.test.skip=true clean package

@echo JAR-Datei (sog. "Fat Jar") in Ordner "target" angelegt,
@echo kann mit folgendem Befehl gestartet werden:
@echo
@echo    java -jar restapidemo-0.0.1-SNAPSHOT.jar gestartet

