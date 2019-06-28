package de.mide.restapidemo.rest3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Diese RestController-Klasse zeigt wie Daten über einen HTTP-Post-Request empfangen werden können.
 * Ein POST-Request anstelle eines GET-Requests sollte immer dann verwendet werden, wenn (Business-)Daten vom
 * REST-Service gespeichert werden müssen.
 * Im Gegensatz zu einem GET-Request kann ein POST-Request nicht einfach durch Eingabe einer URL in den
 * Web-Browser abgesetzt werden; es wird dafür z.B. ein Browser-Plugin wie z.B.
 * <a href="https://restlet.com/modules/client/" target="_blank">Restlet</a> für Chrome benötigt.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RestController
@RequestMapping(value = "/rest3")
public class RestControllerFuerPostRequest {

    /** Log-Objekt, auf das nur Methoden aus dieser Klasse schreiben dürfen. */
    private static final Logger LOGGER = LoggerFactory.getLogger( RestControllerFuerPostRequest.class );

    
    /**
     * REST-Methode zum Enpfang von Daten via POST-Request. Normalerweise würden diese Daten in einen Datenbank geschrieben,
     * aber für dieses Beispiel schreiben wir die Daten nur auf den Logger.
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a target="_blank" href="http://localhost:8080/rest3/datenViaPostRequestEmpfangen">http://localhost:8080/rest3/datenViaPostRequestEmpfangent</a> 
     * 
     * @param payloadString  Vom Client geschickte Nutzdaten, werden einfach auf Logger geschrieben.
     * 
     * @return  String mit Erfolgsmeldung für Client, z.B. "Daten (18 Zeichen) empfangen.".
     */
    @RequestMapping(value = "/datenViaPostRequestEmpfangen", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)    
    public String datenViaPostRequestEmpfangen( @RequestBody String payloadString) {                    
        
        LOGGER.info("Daten via Post-Request empfangen: \"{}\"", payloadString);
                
        String antwortString = "Daten (" + payloadString.length() + " Zeichen) empfangen.";
        
        return antwortString; 
    }

}
