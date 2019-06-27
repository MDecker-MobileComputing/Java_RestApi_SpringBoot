package de.mide.restapidemo.rest2;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Diese REST-Controller-Klasse ist für URLs, die mit {@code /rest2} anfangen, zuständig.
 * Es werden verschiedene Möglichkeiten für die Übergaben von Daten beim Aufruf einer
 * REST-Methode gezeigt.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RestController
@RequestMapping(value = "/rest2")
public class ParameterRestController {

    /**
     * REST-Methode zur Berechnung der Summe von zwei Zahlen.<br>
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest2/addieren?zahl1=123&amp;zahl2=234">http://localhost:8080/rest2/addieren?zahl1=123&amp;zahl2=234</a><br><br>
     * 
     * Beide URL-Parameter sind Pflicht-Parameter, es wird also eine Exception geworfen, wenn einer vergessen wird; weiter müssen
     * beide Parameter auch gültige int-Werte sein.
     * 
     * @param zahl1  Erster Summand
     * 
     * @param zahl2  Zweiter Summand
     * 
     * @return  Summe der beiden als URL-Parameter übergebenen Zahlen als String, z.B. "Summe=123".
     */
    @RequestMapping(value = "/addieren", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)    
    public String addieren( @RequestParam(value="zahl1", required=true, defaultValue="") int zahl1,
                            @RequestParam(value="zahl2", required=true, defaultValue="") int zahl2 ) {
            
        int summe = zahl1 + zahl2;
        
        return "Summe=" + summe;        
    }
    
    
    /**
     * REST-Methode zur Berechnung der Differenz von zwei Zahlen.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest2/subtrahieren/150/minus/25">http://localhost:8080/rest2/subtrahieren/150/minus/25</a><br><br>
     * 
     * @param zahl1  Zahl, von der {@code zahl2} abgezogen wird. 
     * 
     * @param zahl2  Zahl, die von {@code zahl1} abgezogen wird.
     * 
     * @return  Differenz der beiden als Pfad-Parameter übergebenen Zahle als String, z.B. "Differenz=99".
     */
    @RequestMapping(path="/subtrahieren/{zahl1}/minus/{zahl2}")
    public String subtrahieren( @PathVariable(value="zahl1") int zahl1,
                                @PathVariable(value="zahl2") int zahl2 ) {
                    
        int differenz = zahl1 - zahl2;
        
        return "Differenz=" + differenz;        
    }
    
}
