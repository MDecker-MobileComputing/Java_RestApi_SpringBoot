package de.mide.restapidemo.rest;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Ein Objekt dieser Klasse soll von einer REST-Methode zurückgegeben werden.
 * Dabei werden die Rückgabe-Werte aller öffentlichen Methoden in einen JSON-String
 * serialisiert.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class DatumUndZeitWrapper {

    /** Von dieser Klasse gekapseltes Datums-Objekt mit Zeit und Datum. */
    protected Date _date = new Date();

    /** Formatierer für {@link Date}-Objekt, der nur das Datum im deutschen Format ausgibt. */
    protected SimpleDateFormat _datumsFormatierer = new SimpleDateFormat("dd.MM.yyyy");

    /** Formatierer für {@link Date}-Objekt, der nur die Uhrzeit im deutschen Format ausgibt. */
    protected SimpleDateFormat _zeitFormatierer = new SimpleDateFormat("hh:mm");


    /** 
     * Rückgabewert dieser öffentlichen Getter-Methode wird in JSON-Objekt gepackt. 
     * 
     * @return  String mit Datum im deutschen Format
     */
    public String getDatum() {

        return _datumsFormatierer.format( _date );
    }

    
    /** 
     * Rückgabewert dieser öffentlichen Getter-Methode wird in JSON-Objekt gepackt. 
     * 
     * @return  String mit Uhrzeit im deutschen Format
     */    
    public String getZeit() {

        return _zeitFormatierer.format( _date );
    }


}
