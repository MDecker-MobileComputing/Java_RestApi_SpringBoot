# Rest-API with Spring Boot #

<br>

This repository contains a small [Spring Boot](https://spring.io/projects/spring-boot) application that shows how to implement a basic REST API.

<br>

Additional information concerning this application can be found on [the Wiki pages for this repository](https://github.com/MDecker-MobileComputing/Java_RestApi_SpringBoot/wiki), but these pages are in German only.

<br>

See [this repository](https://github.com/MDecker-MobileComputing/Java_RestAPI_BmiBerechnung/) for another Java application that implements a X?!e4MTptQREST API, but not with *Spring Boot*. 

<br>

----

## Running the application ##

<br>

To start the application (i.e. to start the web server) you have several options:

* On the command line (you need to have installed [Apache Maven](https://maven.apache.org/index.html)):

     * Run script `maven_run.bat` in Window's *cmd.exe* ("DOS Box").

     * Enter the following command (starts goal `run` of plugin `spring-boot`):
       > mvn spring-boot:run
  <br>
  
You can also generate a so called **Fat Jar** by running script `mvn_package.bat`. 
This should produce a file with suffix `jar` in folder `target/`.
This file contains all required dependencies (that's why is called "fat") and can be executed by a command like the following one:

    java -jar restapidemo-0.0.1-SNAPSHOT.jar

<br>

See [this page in the Wiki for this repository](https://github.com/MDecker-MobileComputing/Java_RestApi_SpringBoot/wiki/Maven-in-Eclipse)
on how to define a *Run Configuration* for running Maven goal `spring-boot:run` in **Eclipse IDE**.

<br>      

----

## JavaDoc ##

<br>

Execute script `maven_javadoc.bat` in Window's *cmd.exe*; the output files are written to folder `target/site/apidocs`.
	 
<br>

----

## License ##

<br>

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

See libraries and frameworks used as dependencies (see file `pom.xml`) have their own licenses.

<br>
