
@REM Phase "test" zur Ausführung der Unit-Tests aufrufen
mvn test

@REM In pom.xml ist "jacoco" konfiguriert, also werden Coverage-Reports erzeugt 
@REM und im Unterordner target/site/jacoco abgelegt.

@REM Nur Tests in einer bestimmten Unit-Test-Klasse ausführen:
@REM mvn test -Dtest=PostRequestRestControllerTests

@REM Nur bestimmte Unit-Test-Methode ausführen:
@REM mvn test -Dtest=PostRequestRestControllerTests#testDatenViaPostRequestEmpfangen
