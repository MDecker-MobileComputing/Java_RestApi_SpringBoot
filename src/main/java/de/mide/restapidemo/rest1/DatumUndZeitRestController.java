package de.mide.restapidemo.rest1;


import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Diese REST-Controller-Klasse ist für URLs, die mit {@code /rest1} anfangen, zuständig.
 * Die enthaltenen REST-Methoden erwarten keine Parameter und zeigen verschiedene
 * Möglichkeiten Daten zurückzugeben.
 * Alle Methoden geben die aktuelle Systemzeit (Datum + Uhrzeit) zurück, aber in unterschiedlichen Formaten.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RestController
@RequestMapping(value = "/rest1")
public class DatumUndZeitRestController {

    /**
     * REST-Methode, die aktuelles Datum+Zeit als String zurückgibt.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest1/datumUndZeitAlsString">http://localhost:8080/rest1/datumUndZeitAlsString</a>
     *
     * @return  String, der als HTTP-Antwort an den Client zurückgeschickt wird.
     */
    @RequestMapping(value = "/datumUndZeitAlsString", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String datumUndZeitAlsString() {

        Date heuteDate = new Date();

        String heuteDatumString = heuteDate.toString();

        return heuteDatumString;
    }


    /**
     * REST-Methode, die aktuelles Datum+Zeit als (JSON-)Objekt zurückgibt.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest1/datumUndZeitAlsObjekt">http://localhost:8080/rest1/datumUndZeitAlsObjekt</a>
     * <br><br>
     * 
     * Es wird eine Instanz der Klasse {@link DatumUndZeitWrapper} erzeugt und zurückgegeben. Die Werte aller öffentlichen
     * Getter-Methoden dieses Objekts werden in den JSON-String gepackt. 
     *
     * @return  Objekt, das (nach Serialisierung in das JSON-Format) an den Client zurückgeschickt wird.
     *          Beispiel-JSON-String: <pre>{"datum":"27.06.2019","zeit":"11:03"}</pre> 
     */
    @RequestMapping(value = "/datumUndZeitAlsObjekt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DatumUndZeitWrapper datumUndZeitAlsObjekt() {

        DatumUndZeitWrapper datumUndZeit = new DatumUndZeitWrapper();

        return datumUndZeit;
    }
    
    
    /**
     * Rest-Methode, die aktuelles Datum+Zeit als String zurückgibt mit bestimmten <a href="https://httpstatuses.com/">HTTP-Status-Code</a>, 
     * nämlich "202" (Accepted).  
     * Hierzu wird der Antwort-String in ein Objekt der Spring-spezifischen Klasse {@link ResponseEntity} verpackt; an diesem Objekt 
     * kann ein spezieller HTTP-Status-Code gesetzt werden sowie bei Bedarf auch bestimmte HTTP-Response-Header.
     * Diese HTTP-Response-Header bekommen in diesem Beispiel das Prefix "X-", weil sie nur zum Testen sind, siehe auch
     * <a href="https://stackoverflow.com/a/51500375/1364368" target="_blank">diese Antwort auf <i>stackoverflow.com</i></a>.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest1/datumUndZeitMitResponseEntity">http://localhost:8080/rest1/datumUndZeitMitResponseEntity</a>
     * <br><br>
     * 
     * Den Status-Code und die eigenen HTTP-Response-Header für einen HTTP-Request zu dieser URL kann man z.B. im Firefox-Browser über 
     * "Tools | Web Developer | Network" anschauen.
     * 
     * @return  Objekt mit HTTP-Response-Antwort und HTTP-Status-Code 202 (Accepted).
     */
    @RequestMapping(value = "/datumUndZeitMitResponseEntity", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> datumUndZeitMitResponseEntity() {
        
        Date heuteDate = new Date();

        String heuteDatumString = heuteDate.toString();
        
        HttpHeaders eigeneResponseHeader = new HttpHeaders();
        eigeneResponseHeader.set("X-Generator", "RestController");
        eigeneResponseHeader.set("X-Framework", "Spring Boot"   );
                  
        return new ResponseEntity<String>(heuteDatumString, eigeneResponseHeader, HttpStatus.ACCEPTED);
    }
        
}
