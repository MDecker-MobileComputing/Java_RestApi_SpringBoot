@REM JavaDoc Plugin: https://maven.apache.org/plugins/maven-javadoc-plugin/examples/test-javadocs.html

@REM JavaDoc für Unit-Test-Klassen unter src/test/java erzeugen
@REM Goal "test-javadoc" von Plugin "javadoc" ausführen
mvn javadoc:test-javadoc -Dshow=private
@REM -Dshow=private: Auch Javadoc für private Methoden und Attribute erzeugen

@echo JavaDoc-Dateien in Ordner "target/site/testapidocs" geschrieben
