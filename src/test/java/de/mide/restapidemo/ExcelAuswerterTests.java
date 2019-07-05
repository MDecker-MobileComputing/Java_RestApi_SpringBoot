package de.mide.restapidemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.mide.restapidemo.rest3.ExcelAuswerter;


/**
 * Tests für Klasse {@link ExcelAuswerter}; diese Klasse testet also keine REST-Methode.
 * <br><br>
 *
 * Die als Test-Daten verwendeten Excel-Dateien befinden sich im Ordner {@code src/main/resources/}.
 * Wenn in diesem Verzeichnis z.B. eine Datei mit Name {@code dateiname.xlsx} liegt,
 * dann kann der {@link InputStream} zum Einlesen dieser Datei mit dem folgenden Methodenaufruf
 * abgefragt werden: {@code this.getClass().getResourceAsStream("dateiname.xlsx")}
 * <br><br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelAuswerterTests {

    /**
     * Einstiegspfad für Suche nach Test-Dateien. Pfad muss mit Schrägstrich anfagen, damit die Dateien
     * relativ zum Verzeichnis {@code src/main/resources/} gesucht werden.
     */
    public static final String PFAD_TESTDATEIEN = "/TestDateienFuerUnitTests/";


    /**
     * Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * In Zelle A1 steht ein String-Wert.
     *
     * @throws Exception  Fehler während Ausführung Test
     */
    @Test
    public void testHappyPathString() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(PFAD_TESTDATEIEN + "TestExcel_1_HappyPathString.xlsx");
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);

        // Methode unter Test aufrufen
        String ergebnisString = ExcelAuswerter.getWertZelleA1(is);

        assertEquals("Unerwarteter String-Wert für Zelle A1 in Excel-Datei zurückgeliefert.",
                     "String in Zelle A1: \"abc def\"",
                     ergebnisString);
    }


    /**
     * Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * In Zelle A1 steht ein Numeric-Wert.
     *
     * @throws Exception  Fehler während Ausführung Test
     */
    @Test
    public void testHappyPathNumeric() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(PFAD_TESTDATEIEN + "TestExcel_2_HappyPathNumeric.xlsx");
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);

        // Methode unter Test aufrufen
        String ergebnisString = ExcelAuswerter.getWertZelleA1(is);

        assertEquals("Unerwarteter Numeric-Wert für Zelle A1 in Excel-Datei zurückgeliefert.",
                     "Numeric in Zelle A1: \"123,000000\"",
                     ergebnisString);
    }


    /**
     * Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * In Zelle A1 steht eine Formel.
     *
     * @throws Exception  Fehler während Ausführung Test
     */
    @Test
    public void testHappyPathFormel() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(PFAD_TESTDATEIEN + "TestExcel_3_HappyPathFormel.xlsx");
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);

        // Methode unter Test aufrufen
        String ergebnisString = ExcelAuswerter.getWertZelleA1(is);

        System.out.println("testHappyPathFormel(): ergebnisString=" + ergebnisString);

        assertEquals("Unerwarteter Numeric-Wert für Zelle A1 in Excel-Datei zurückgeliefert.",
                     "Formel in Zelle A1: \"2+3\"",
                     ergebnisString);
    }


    /**
     * Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * Fehlerfall, Zeile 1 fehlt.
     *
     * @throws Exception  Fehler während Ausführung Test
     */
    @Test
    public void testZeileFehlt() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(PFAD_TESTDATEIEN + "TestExcel_4_ZeileFehlt.xlsx");
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);

        // Methode unter Test aufrufen
        String ergebnisString = ExcelAuswerter.getWertZelleA1(is);

        assertEquals("Unerwarteter Wert für fehlende Zeile 1 in Excel-Datei zurückgeliefert.",
                     "Zeile 1 nicht gefunden.",
                     ergebnisString);
    }


    /**
     * Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * Fehlerfall, Zeile 1 ist vorhanden, aber die erste Zelle darin (also Zelle A1) fehlt.
     *
     * @throws Exception  Fehler während Ausführung Test
     */
    @Test
    public void testZelleFehlt() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(PFAD_TESTDATEIEN + "TestExcel_5_ZelleFehlt.xlsx");
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);

        // Methode unter Test aufrufen
        String ergebnisString = ExcelAuswerter.getWertZelleA1(is);

        assertEquals("Unerwarteter Wert für fehlende Zelle A1 in Excel-Datei zurückgeliefert.",
                     "Zelle A in Zeile 1 nicht gefunden.",
                     ergebnisString);
    }
    
    
    /**
     * Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * Fehlerfall, die Datei ist keine xlsx-Datei. 
     * 
     * @throws Exception  Fehler während Ausführung Test
     */
    @Test
    public void testKeineXlsxDatei() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(PFAD_TESTDATEIEN + "FalschesDateiformat.txt");
        assertNotNull("Testdaten-Fehler: Text-Datei nicht gefunden.", is);


        // Methode unter Test aufrufen
        try {
            ExcelAuswerter.getWertZelleA1(is);
            
            fail("Bei Versuche eine Text-Datei anstelle einer xlsx-Datei zu öffnen wurde keine Exception geworfen.");
        }
        catch (Exception ex) {
            // Erwartete Exception wurde gefangen
        }
    }


    /**
     * Dummy-Test für (Hilfs-)Methode {@link ExcelAuswerter#getWertZelleA1(InputStream)}:
     * Konstruktor wird aufgerufen, damit die Zeile {@code public class ExcelAuswerter}
     * im Coverage-Report nicht mehr rot (d.h. als nicht getestet) markiert ist.
     */     
    @Test
    public void testKonstruktor() {
        
        ExcelAuswerter ea = new ExcelAuswerter();
        
        assertNotNull("Es konnte kein Objekt der Klasse unter Test erzeugt werden.", ea);
    }

}