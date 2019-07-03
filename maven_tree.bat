
@REM Gibt Abhängigkeiten in Form eines Baums aus.
@REM
@REM Ausgabe-Format für einzelne Abhaengigkeiten: <groupdID>:<artifactID>:<type>:<version>:<goal>
@REM
@REM Die im Baum als direkte Kind-Elemente von "de.mide.restapidemo" dargestellten Abhängigkeiten
@REM (also den "+"-Knoten ganz links haben) sind direkt in der pom.xml definiert.
@REM
@REM Doku: https://maven.apache.org/plugins/maven-dependency-plugin/tree-mojo.html

mvn dependency:tree
