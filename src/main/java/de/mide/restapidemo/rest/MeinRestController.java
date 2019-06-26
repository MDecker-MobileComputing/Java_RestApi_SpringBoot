package de.mide.restapidemo.rest;


import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST-Controller-Klasse mit REST-Methoden.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RestController
@RequestMapping(value = "/test")
public class MeinRestController {

    /**
     * REST-Methode, die aktuelles Datum+Zeit als String zurückgibt.
     * <br>
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/test/datumUndZeitAlsString">http://localhost:8080/test/datumUndZeitAlsString</a>
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
     * <br>
     * Bei lokaler Ausführung ist diese REST-Methode unter der folgenden URL verfügbar:
     * <a href="http://localhost:8080/test/datumUndZeitAlsString">http://localhost:8080/test/datumUndZeitAlsObjekt</a>
     *
     * @return  Objekt, das (nach Serialisierung in das JSON-Format) an den Client zurückgeschickt wird.
     */
    @RequestMapping(value = "/datumUndZeitAlsObjekt", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DatumUndZeitWrapper datumUndZeitAlsObjekt() {

        DatumUndZeitWrapper datumUndZeit = new DatumUndZeitWrapper();

        return datumUndZeit;
    }

}
