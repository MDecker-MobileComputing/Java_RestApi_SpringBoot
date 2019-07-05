package de.mide.restapidemo;

import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;


/**
 * Unit-Test-Methoden für die REST-Methoden in Klasse {@link de.mide.restapidemo.rest3.PostRequestRestController}.
 * Alle Test-Methoden in dieser Klasse simulieren POST-Requests.
 * <br><br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostRequestRestControllerTests {
    
    /**
     * Mock-Objekt für die Ausführung der HTTP-Requests.
     * Es werden HTTP-Requests zu REST-Methoden durch Aufruf der Methode {@link MockMvc#perform(RequestBuilder)}
     * simuliert.
     */
    @Autowired
    private MockMvc _mock;
    
    /** Matcher für HTTP-Status-Code "200" (OK). */
    private static final ResultMatcher MATCHER_HTTP_STATUS_200 = status().isOk();
    
    /** ResultHandler um HTTP-Request auf die Konsole zu schreiben. */
    private static final ResultHandler RESULT_HANDLER_PRINT = print(); 


    /**
     * Test-Methode für die REST-Methode {@code /rest3/datenViaPostRequestEmpfangen} via POST-Request.
     *
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDatenViaPostRequestEmpfangen() throws Exception {

        final String textPayload = "Lorem Ipsum"; // 11 Zeichen

        RequestBuilder requestBuilder = post("/rest3/datenViaPostRequestEmpfangen").contentType(MediaType.TEXT_PLAIN).content(textPayload);

        ResultActions ra1 = _mock.perform( requestBuilder ).andDo( RESULT_HANDLER_PRINT ).andExpect( MATCHER_HTTP_STATUS_200 );

        ResultMatcher responseMatcher = content().string( equalTo("Daten (11 Zeichen) empfangen.") );
        ra1.andExpect(responseMatcher);        
    }
    
    
    /**
     * Test-Methode für die REST-Methode {@code /rest3/dateiHochladen} via POST-Request (lädt echte Binär-Datei hoch).
     * Diese Methode verwendet <b>nicht</b> {@link #RESULT_HANDLER_PRINT}, weil sonst eine Binär-Datei auf die Konsole geschrieben würde. 
     * 
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDateiHochladen() throws Exception {
        
        InputStream is = this.getClass().getResourceAsStream(ExcelAuswerterTests.PFAD_TESTDATEIEN + "TestExcel_1_HappyPathString.xlsx");
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);        
        

        byte[] xlsxByteArray = IOUtils.toByteArray(is); // kann IOException werfen
        
        assertTrue("Umwandlung von InputStream in byte[] hat nicht geklappt.", xlsxByteArray.length > 0);
        
        RequestBuilder requestBuilder = post("/rest3/dateiHochladen").contentType(MediaType.MULTIPART_FORM_DATA).content(xlsxByteArray);
        
        /* ResultActions ra1 = */ _mock.perform( requestBuilder ).andExpect( MATCHER_HTTP_STATUS_200 );                       
    }

}
