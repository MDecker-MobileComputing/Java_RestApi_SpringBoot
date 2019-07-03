package de.mide.restapidemo.rest3;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Klasse zum Auswerten von Excel-Dateien, die mit einem POST-Request an eine REST-Methode hochgeladen worden sind.
 * Hierzu wird die Bibliothek <a href="https://poi.apache.org/" target="_blank">Apache POI - the Java API for Microsoft Documents</a> verwendet. 
 * Siehe auch die <a href="https://poi.apache.org/apidocs/dev/index.html" target="_blank">API-Dokumentation zu POI</a>
 * und die <a href="https://en.wikipedia.org/wiki/Apache_POI" target="_blank">Wikipedia-Seite</a>.
 * <br><br>
 * 
 * "POI" muss als Abhängigkeit in der Datei {@code pom.xml} deklariert werden mit {@code groupID=org.apache.poi} und
 * {@code artifactID=poi-ooxml}, die neueste Version kann man z.B. auf 
 * <a href="https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml" target="_blank">mvnrepository.com</a> nachschauen.
 * <br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class ExcelAuswerter {
    
    /** Log-Objekt, auf das nur Methoden aus dieser Klasse schreiben dürfen. */
    private static final Logger LOGGER = LoggerFactory.getLogger( ExcelAuswerter.class );
    
    
    /** 
     * Diese Methode versucht den Wert aus der Zelle A1 (ganz links oben) im ersten Tabellenblatt aus einer Excel-Datei auszulesen. 
     * 
     * @param inputStreamExcelDatei  Input-Stream, aus dem Excel-Datei (xlsx-Format) gelesen werden kann.
     * 
     * @return  String, der Wert von Zelle A1 beschreibt; der Inhalt kann auch sein, dass das erste Tabellenblatt, die erste
     *          Zeile auf diesem Tabellenblatt, oder die erste Zelle in dieser Zeile nicht existiert.
     * 
     * @throws IOException  Fehler bei Ein-/Ausgabe-Verarbeitung von Excel-Datei.
     * 
     * @throws IllegalArgumentException Erstes Tabellenblatt (Index=0) existiert nicht.
     */
    public static String getWertZelleA1(InputStream inputStreamExcelDatei) throws IOException, IllegalArgumentException {
        
        String ergebnisString = "";
        
        XSSFWorkbook workbook = new XSSFWorkbook(inputStreamExcelDatei); // kann IOException werfen
        
        
        // Erstes Tabellenblatt holen
        XSSFSheet sheet = workbook.getSheetAt(0); // kann IllegalArgumentException werfen
        
                
        // Zelle aus Tabellenblatt holen
        XSSFRow  ersteZeile = sheet.getRow(0);
        LOGGER.info("Erste Zeile in Excel-Datei: {}", ersteZeile);
        if (ersteZeile == null) { // auch wenn die zweite oder höhere Zeile existiert kann die erste Zeile nicht existieren
            
            ergebnisString = "Erste Zeile existiert nicht auf Tabellenblatt 1.";
            
        } else {
                    
            XSSFCell ersteZelle = ersteZeile.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
            LOGGER.info("Erste Zelle in erster Zeile: {}", ersteZelle);
            if (ersteZelle == null) {
            
                ergebnisString = "Erste Zeile Zelle in erste Zeile existiert nicht.";
                
            } else {
                
                // Wert aus Zelle herausholen
                CellType zellenTyp = ersteZelle.getCellType();         
                LOGGER.info("Typ der Zelle A1 in hochgeladener xlsx-Datei: {}", zellenTyp);
                
                switch (zellenTyp) {
                
                    case STRING:
                        String stringWert = ersteZelle.getStringCellValue();
                        ergebnisString = String.format("String in Zelle A1: \"%s\"", stringWert); 
                    break;
        
                    case NUMERIC:
                        double zahlenWert = ersteZelle.getNumericCellValue(); 
                        ergebnisString = String.format("Numeric in Zelle A1: \"%d\"", zahlenWert);
                    break;
                        
                    case BLANK:
                        ergebnisString = "Zelle A1 ist leer.";
                    break;
                
                    default: 
                        ergebnisString = "Nicht unterstützter Typ der Zelle A1: \"" + zellenTyp + "\"";        
                }
            }
        }
        
        workbook.close(); // sollte auf jeden Fall gemacht werden

        return ergebnisString;
    }

}
