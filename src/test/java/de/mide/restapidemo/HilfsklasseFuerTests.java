package de.mide.restapidemo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;


/**
 * Klasse enthält Klassenvariablen, die von mehreren Testklassen benötigt werden.
 * <br><br><br>
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class HilfsklasseFuerTests {
	
    /**
     * Einstiegspfad für Suche nach Test-Dateien. Pfad muss mit Schrägstrich anfagen, damit die Dateien
     * relativ zum Verzeichnis {@code src/main/resources/} gesucht werden.
     */
    public static final String PFAD_TESTDATEIEN = "/TestDateienFuerUnitTests/";	
	
    /** 
     * Matcher für HTTP-Status-Code "200" (OK). 
     */
    public static final ResultMatcher MATCHER_HTTP_STATUS_200 = status().isOk();

    /** 
     * Matcher für HTTP-Status-Code "202" (Accepted). 
     */
    public static final ResultMatcher MATCHER_HTTP_STATUS_202 = status().isAccepted();
    
    /** 
     * ResultHandler um HTTP-Request auf die Konsole zu schreiben. 
     */
    public static final ResultHandler RESULT_HANDLER_PRINT = print(); 	

}
