package de.mide.restapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse, startet die Applikation und damit auch den integrierten Webserver.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@SpringBootApplication
public class RestdemoApplicationMain {

    /**
     * Einstiegs-Methode.
     * 
     * @param args  Kommando-Zeilen-Argumente, werden einfach an Spring Boot durchgereicht.
     */
	public static void main(String[] args) {
	    
		SpringApplication.run(RestdemoApplicationMain.class, args);
	}

}
