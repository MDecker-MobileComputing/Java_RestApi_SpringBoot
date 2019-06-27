package de.mide.restapidemo.rest1;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Ein Objekt dieser Klasse soll von einer REST-Methode zurückgegeben werden.
 * Dabei werden die Rückgabe-Werte aller öffentlichen Methoden in einen JSON-String serialisiert.
 * Der Name für den JSON-Key wird aus dem Namen der Getter-Methode abgelegt; z.B. würde
 * der Rückgabewert einer Getter-Methode mit Namen {@code getAbc()} unter dem Key "abc" in den
 * JSON-String gepackt.
 * <br>
 * Eine Instanz dieser Klasse kapselt ein {@link Date}-Objekt. 
 * 
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class DatumUndZeitWrapper {

    /** Von dieser Klasse gekapseltes {@link Date}-Objekt mit Zeit und Datum. */
    protected Date _date = null;

    /** Formatierer für {@link Date}-Objekt, der nur das Datum im deutschen Format ausgibt. */
    protected SimpleDateFormat _datumsFormatierer = new SimpleDateFormat("dd.MM.yyyy");

    /** Formatierer für {@link Date}-Objekt, der nur die Uhrzeit (Stunde und Minute, aber nicht Sekunde) im deutschen Format ausgibt. */
    protected SimpleDateFormat _zeitFormatierer = new SimpleDateFormat("hh:mm");

    
    /**
     * Default-Konstruktor. Es wird ein {@link Date}-Objekt erzeugt, das die aktuelle Systemzeit enthält. 
     */
    public DatumUndZeitWrapper() {
        
        _date = new Date();
    }
    

    /**
     * Copy-Konstruktor, über den {@link Date}-Objekt, das von der zu erzeugenden Instanz dieser Klasse gekapselt werden soll, übergeben wird.
     * 
     * @param date  {@link Date}-Objekt, das von dieser Wrapper-Klasse gekapselt werden soll.
     */
    public DatumUndZeitWrapper(Date date) {
        
        _date = date;
    }


    /** 
     * Rückgabewert dieser öffentlichen Getter-Methode wird unter dem Key "datum" in das JSON-Objekt gepackt. 
     * 
     * @return  String mit Datum im deutschen Format, z.B. "15.12.2019".
     */
    public String getDatum() {

        return _datumsFormatierer.format( _date );
    }

    
    /** 
     * Rückgabewert dieser öffentlichen Getter-Methode wird unter dem Key "zeit" in das JSON-Objekt gepackt. 
     * 
     * @return  String mit Uhrzeit im deutschen Format, z.B. "23:15".
     */    
    public String getZeit() {

        return _zeitFormatierer.format( _date );
    }


}
