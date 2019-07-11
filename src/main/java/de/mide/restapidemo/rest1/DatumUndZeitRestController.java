package de.mide.restapidemo.rest1;



import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


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

    /** Log-Objekt, auf das nur Methoden aus dieser Klasse schreiben dürfen. */
    private static final Logger LOGGER = LoggerFactory.getLogger( DatumUndZeitRestController.class );

    
    /** 
     * <a href="https://github.com/FasterXML/jackson" target="_blank">Jackson</a>-Serialisierer-Objekt 
     * um Java-Objekte in JSON-Strings umzuwandenln.
     * Das Objekt wird erst bei Bedarf in der jeweiligen REST-Methode erzeugt.
     */
    protected ObjectMapper _jacksonSerializer = null;

    
    /**
     * REST-Methode, die aktuelles Datum+Zeit als String zurückgibt.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest1/datumUndZeitAlsString">http://localhost:8080/rest1/datumUndZeitAlsString</a>
     *
     * @return  String, der als HTTP-Antwort an den Client zurückgeschickt wird.
     *          Beispiel: <pre>Thu Jul 04 14:23:54 CEST 2019</pre>
     */
    @RequestMapping(value = "/datumUndZeitAlsString", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String datumUndZeitAlsString() {

        Date heuteDate = new Date();

        String heuteDatumString = heuteDate.toString();

        return heuteDatumString;
    }


    /**
     * REST-Methode, die aktuelles Datum+Zeit als JSON-Objekt zurückgibt.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest1/datumUndZeitAlsObjekt">http://localhost:8080/rest1/datumUndZeitAlsObjekt</a>
     * <br><br>
     * 
     * Es wird eine Instanz der Klasse {@link DatumUndZeitWrapper} erzeugt und zurückgegeben. Die Werte aller öffentlichen
     * Getter-Methoden dieses Objekts werden in den JSON-String gepackt. 
     *
     * @return  Nach JSON serialisierte Instanz von {@link DatumUndZeitWrapper}. 
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
     * Im vorliegenden Beispiel werden die beiden HTTP-Response-Headers 
     * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Server" target="_blank">"Server"</a> und
     * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control" target="_blank">"Cache-Control"</a> gesetzt.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/rest1/datumUndZeitMitResponseEntity">http://localhost:8080/rest1/datumUndZeitMitResponseEntity</a>
     * <br><br>
     * 
     * Den Status-Code und die eigenen HTTP-Response-Header für einen HTTP-Request zu dieser URL kann man z.B. im Firefox-Browser über 
     * "Tools | Web Developer | Network" anschauen, siehe auch 
     * <a target="_blank" href="https://github.com/MDecker-MobileComputing/Java_RestApi_SpringBoot/wiki/HTTP-Response-Header-setzen">diese Wiki-Seite (mit Screenshot)</a>.
     * 
     * @return  Objekt mit HTTP-Response-Antwort und HTTP-Status-Code 202 (Accepted) sowie zwei Response-Headern.
     */
    @RequestMapping(value = "/datumUndZeitMitResponseEntity", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> datumUndZeitMitResponseEntity() {
        
        Date heuteDate = new Date();

        String heuteDatumString = heuteDate.toString();
        
        HttpHeaders eigeneResponseHeader = new HttpHeaders();
        eigeneResponseHeader.set("Server"       , "Spring Boot RestController");
        eigeneResponseHeader.set("Cache-Control", "no-cache"                  );
                  
        return new ResponseEntity<String>(heuteDatumString, eigeneResponseHeader, HttpStatus.ACCEPTED);
    }
    
    
    /**
     * REST-Methode, die aktuelles Datum+Zeit als JSON-Objekt zurückgibt, wobei wie von Methode {@link #datumUndZeitMitResponseEntity()}
     * ein bestimmter HTTP-Status-Code sowie HTTP-Response-Header gesetzt werden.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a target="_blank" href="http://localhost:8080/rest1/datumUndZeitMitResponseEntityImJsonFormat">http://localhost:8080/rest1/datumUndZeitMitResponseEntityImJsonFormat</a>
     * 
     * @return  Nach JSON serialisierte Instanz von {@link DatumUndZeitWrapper}. 
     *          Beispiel-JSON-String: <pre>{"datum":"27.06.2019","zeit":"11:03"}</pre><br>          
     *          Wenn bei der Serialisierung eine Exception auftritt, dann wird der HTTP-Status-Code
     *          <a target="_blank" href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/500">500 (Internal Server Error</a>
     *          zurückgegeben.
     */
    @RequestMapping(value = "/datumUndZeitMitResponseEntityImJsonFormat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> datumUndZeitMitResponseEntityImJsonFormat() {

        HttpHeaders eigeneResponseHeader = new HttpHeaders();
        eigeneResponseHeader.set("Server"       , "Spring Boot RestController");
        eigeneResponseHeader.set("Cache-Control", "no-cache"                  );
        
        if (_jacksonSerializer == null) {
            _jacksonSerializer = new ObjectMapper();
            _jacksonSerializer.enable(SerializationFeature.INDENT_OUTPUT); // "Pretty Printing" einschalten
            
            LOGGER.info("Jackson-Serialisierer erzeugt.");
        }
        
        DatumUndZeitWrapper datumUndZeit = new DatumUndZeitWrapper();
        
        try {

            String jsonResultString = _jacksonSerializer.writeValueAsString(datumUndZeit); 
                              
            return new ResponseEntity<String>(jsonResultString, eigeneResponseHeader, HttpStatus.ACCEPTED);
        }
        catch (JsonProcessingException ex) {
            
            LOGGER.error("Fehler bei Serialisierung von Java-Objekt.", ex);
            
            return new ResponseEntity<String>(eigeneResponseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }    
        
}
