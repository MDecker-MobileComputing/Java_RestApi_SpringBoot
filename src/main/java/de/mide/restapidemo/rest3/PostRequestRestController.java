package de.mide.restapidemo.rest3;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * Diese RestController-Klasse zeigt wie Daten über einen HTTP-Post-Request empfangen werden können.
 * Ein POST-Request anstelle eines GET-Requests sollte immer dann verwendet werden, wenn (Business-)Daten vom
 * REST-Service gespeichert werden müssen.
 * Im Gegensatz zu einem GET-Request kann ein POST-Request nicht einfach durch Eingabe einer URL in den
 * Web-Browser abgesetzt werden; es wird dafür z.B. eine Browser-Erweiterung wie z.B.
 * <a href="https://restlet.com/modules/client/" target="_blank">Restlet</a> für Chrome benötigt (siehe 
 * <a href="https://github.com/MDecker-MobileComputing/Java_RestApi_SpringBoot/wiki/POST-Requests" target="_blank">diese Wiki-Seite</a>
 * für die Verwendung dieser Browser-Erweiterung zum Aufruf der REST-Methode {@link #datenViaPostRequestEmpfangen(String)}).
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RestController
@RequestMapping(value = "/rest3")
public class PostRequestRestController {

    /** Log-Objekt, auf das nur Methoden aus dieser Klasse schreiben dürfen. */
    private static final Logger LOGGER = LoggerFactory.getLogger( PostRequestRestController.class );

    
    /**
     * REST-Methode zum Empfang von Daten via POST-Request. Normalerweise würden diese Daten in einen Datenbank geschrieben,
     * aber für dieses Beispiel schreiben wir die Daten nur auf den Logger.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a target="_blank" href="http://localhost:8080/rest3/datenViaPostRequestEmpfangen">http://localhost:8080/rest3/datenViaPostRequestEmpfangen</a>
     * <br><br>
     * 
     * Da POST-Requests nicht direkt mit dem Browser erzeugt werden können, findet sich (bei lokaler Ausführung) unter der folgenden
     * URL eine HTML-Seite mit einem Formular, mit der Daten an diese REST-Methode geschickt werden können:
     * <a target="_blank" href="http://localhost:8080/index.html">http://localhost:8080/index.html</a><br>
     * 
     * @param payloadString  Vom Client geschickte Nutzdaten, werden einfach auf Logger geschrieben, bei Übertragung
     *                       von Web-Formular mit <a target="_blank" href="https://www.w3schools.com/tags/ref_urlencode.asp">URL-Kodierung</a> 
     *                       (d.h. Sonderzeichen wie Leerzeichen und Umlaute durch "+" bzw. Prozent-Codes ersetzt).
     * 
     * @return  String mit Erfolgsmeldung für Client, z.B. "Daten (18 Zeichen) empfangen.".
     */
    @RequestMapping(value = "/datenViaPostRequestEmpfangen", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)    
    public String datenViaPostRequestEmpfangen( @RequestBody String payloadString) {                    
        
        LOGGER.info("Daten via Post-Request empfangen: \"{}\"", payloadString);
                
        String antwortString = "Daten (" + payloadString.length() + " Zeichen) empfangen.";
        
        return antwortString; 
    }
    

    /**
     * Rest-Methode zum Empfang hochgeladener Excel-Datei ("neues" xlsx-Datei).
     * Siehe auch <a href="http://zetcode.com/springboot/uploadfile/" target="_blank">dieses Tutorial</a>.
     * <br><br>
     * 
     * Es wird der Wert der Zelle A1 auf dem ersten Tabellenblatt ausgelesen und an den Browser zurückgegeben
     * sowie auf den Logger geschrieben. Die Auswertung der Excel-Datei geschieht unter Verwendung der
     * Bibliothek <a href="https://poi.apache.org" target="_blank">Apache POI</a> in der Methode  
     * {@link ExcelAuswerter#getWertZelleA1(InputStream)}.
     * <br><br>
     * 
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a target="_blank" href="http://localhost:8080/rest3/dateiHochladen">http://localhost:8080/rest3/dateiHochladen</a>
     * 
     * @param datei  Hochgeladene Datei (Parameter-Name muss mit Wert von Attribut {@code name} von {@code input}-Tag übereinstimmen).
     * 
     * @return  String mit Inhalt der Zelle A1 aus der hochgeladenen Excel-Datei oder Fehlermeldung.
     */
    @RequestMapping(value = "/dateiHochladen", method = RequestMethod.POST, consumes = {"multipart/form-data"})            
    public String dateiHochladen(@RequestParam MultipartFile datei) {
        
        // Attribute der Datei auslesen
        String dateiName          = datei.getOriginalFilename();        
        long   dateiGroesseBytes  = datei.getSize();        
        String contentTypeString  = datei.getContentType();     
        
        String metaDatenString = String.format("Datei mit Name \"%s\" empfangen (%d Bytes, Content Type \"%s\").", dateiName, dateiGroesseBytes, contentTypeString);        
        LOGGER.info( metaDatenString );
        
        try {
            
            InputStream excelDateiInputStream = datei.getInputStream();
            
            String ergebnisStr = ExcelAuswerter.getWertZelleA1(excelDateiInputStream);
            LOGGER.info( ergebnisStr );
            
            return ergebnisStr;
            
        } catch (Exception ex) {
            
            String fehlerNachricht = "Exception beim Auswerten von Excel-Datei aufgetreten: " + ex.getMessage(); 
            
            LOGGER.error(fehlerNachricht, ex);
            return fehlerNachricht;
        }
    }
    
}
