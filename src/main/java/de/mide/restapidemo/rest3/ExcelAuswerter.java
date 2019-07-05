package de.mide.restapidemo.rest3;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Klasse zum Auswerten von Excel-Dateien, die mit einem POST-Request an eine REST-Methode hochgeladen worden sind.
 * Hierzu wird die Bibliothek <a href="https://poi.apache.org/" target="_blank">Apache POI - the Java API for Microsoft Documents</a> verwendet; 
 * siehe auch die <a href="https://poi.apache.org/apidocs/dev/index.html" target="_blank">API-Dokumentation zu POI</a>
 * und den <a href="https://en.wikipedia.org/wiki/Apache_POI" target="_blank">Wikipedia-Artikel</a>.
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
     * @throws Exception  Fehler bei Auswertung der Excel-Datei  
     */
    public static String getWertZelleA1(InputStream inputStreamExcelDatei) throws Exception {
                
        XSSFWorkbook workbook = new XSSFWorkbook(inputStreamExcelDatei); // kann IOException werfen
        
        String ergebnisString = getWertVonZelleA1(workbook);
                
        workbook.close(); // Excel-Datei muss auf jeden Fall wieder geschlossen werden

        return ergebnisString;        
    }
    
    
    /**
     * Sicheres Auslesen von Wert aus Zelle A1 auf dem ersten Tabellenblatt.
     * Zeile 1 kann nicht-existent sein, auch wenn folgende Zeile existieren; Zelle 1 in Zeile 1 kann nicht-existent
     * sein, auch wenn andere Zellen in dieser Zeile existieren.
     * Die Methode berücksichtigt die Fälle, dass das erste Tabellenblatt, die erste Zeile im ersten Tabellenblatt
     * oder die erste Zelle in der ersten Zeile nicht vorhanden ist.   
     *  
     * @param excelDatei  Excel-Datei, aus der der Wert von Zelle A1 auf dem ersten Tabellenblatt ausgelesen werden soll.
     * 
     * @return  String, der Wert von Zelle A1 beschreibt; der Inhalt kann auch sein, dass das erste Tabellenblatt, die erste
     *          Zeile auf diesem Tabellenblatt, oder die erste Zelle in dieser Zeile nicht existiert.
     */
    protected static String getWertVonZelleA1(XSSFWorkbook excelDatei) {
    	
    	XSSFSheet sheet = null;
                
    	try {    	        	 
    		sheet = excelDatei.getSheetAt(0); // Erstes Tabellenblatt holen 
    	}
    	catch (IllegalArgumentException ex) {
    		// Kann dieser Fehler überhaupt auftreten, weil zumindest in Excel-Anwendung ist es nicht möglich eine XLSX-Datei ganz ohne Tabellenblätter zu erzeugen
    		LOGGER.error("Exception beim Zugriff auf Tabellenblatt 1.", ex);    		
    		return "Tabellenblatt 1 nicht gefunden";
    	}

            	
        XSSFRow ersteZeile = sheet.getRow(0);
        if (ersteZeile == null) {
            // Wenn keine einzige Zelle in der Zeile 1 einen Wert hat, dann gibt es diese Zeile nicht        	
        	return "Zeile 1 nicht gefunden.";
        }
        
        
        XSSFCell ersteZelle = ersteZeile.getCell(0);
        if (ersteZelle == null) {
        	
        	return "Zelle A in Zeile 1 nicht gefunden.";
        }
        
        
        CellType zellenTyp = ersteZelle.getCellType();         
        LOGGER.info("Typ der Zelle A1 in hochgeladener xlsx-Datei: {}", zellenTyp);
        
        switch (zellenTyp) {
        
            case STRING:
                String stringWert = ersteZelle.getStringCellValue();
                return String.format("String in Zelle A1: \"%s\"", stringWert); 

            case NUMERIC:
                double zahlenWert = ersteZelle.getNumericCellValue(); 
                return String.format("Numeric in Zelle A1: \"%f\"", zahlenWert);
                
            case FORMULA:
                String formelWert = ersteZelle.getCellFormula(); // Das "=" am Anfang der Formel ist nicht enthalten
                return String.format("Formel in Zelle A1: \"%s\"", formelWert);
                
            case BLANK:
                return "Zelle A1 ist leer.";
        
            default: 
                return "Nicht unterstützter Typ der Zelle A1: \"" + zellenTyp + "\".";        
        }               
    }

}
