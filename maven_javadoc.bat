@REM JavaDoc Plugin: https://maven.apache.org/plugins/maven-javadoc-plugin/usage.html
@REM Verwendet auch src/main/javadoc/overview.html

mvn javadoc:javadoc -Dshow=private
@REM -Dshow=private: Auch Javadoc für private Methoden und Attribute erzeugen

@echo JavaDoc-Dateien in Ordner "target/site/apidocs" geschrieben


