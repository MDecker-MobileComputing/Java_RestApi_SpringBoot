package de.mide.restapidemo;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;


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
    

    /**
     * Test-Methode für die REST-Methode {@code /rest3/datenViaPostRequestEmpfangen} via POST-Request.
     *
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDatenViaPostRequestEmpfangen() throws Exception {

        final String textPayload = "Lorem Ipsum"; // 11 Zeichen

        RequestBuilder requestBuilder = post("/rest3/datenViaPostRequestEmpfangen").contentType(MediaType.TEXT_PLAIN).content(textPayload);

        ResultActions ra1 = _mock.perform( requestBuilder ).andDo( HilfsklasseFuerTests.RESULT_HANDLER_PRINT ).andExpect( HilfsklasseFuerTests.MATCHER_HTTP_STATUS_200 );

        ResultMatcher responseMatcher = content().string( equalTo("Daten (11 Zeichen) empfangen.") );
        ra1.andExpect(responseMatcher);        
    }
    
    
    /**
     * Test-Methode für die REST-Methode {@code /rest3/dateiHochladen} via POST-Request (lädt echte Binär-Datei hoch).
     * Diese Methode verwendet <b>nicht</b> {@link HilfsklasseFuerTests#RESULT_HANDLER_PRINT}, weil sonst eine Binär-Datei auf die Konsole geschrieben würde. 
     * 
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDateiHochladen() throws Exception {
    	
    	final String dateiName = "TestExcel_1_HappyPathString.xlsx";
        
        InputStream is = this.getClass().getResourceAsStream(HilfsklasseFuerTests.PFAD_TESTDATEIEN + dateiName);
        assertNotNull("Testdaten-Fehler: Excel-Datei nicht gefunden.", is);
                
        MockMultipartFile mockFile = new MockMultipartFile("datei", dateiName, null, is);
        
        RequestBuilder requestBuilder = multipart("/rest3/dateiHochladen").file(mockFile).contentType(MediaType.MULTIPART_FORM_DATA);
                
        ResultActions ra1 = _mock.perform( requestBuilder ).andExpect( HilfsklasseFuerTests.MATCHER_HTTP_STATUS_200 ).andDo( HilfsklasseFuerTests.RESULT_HANDLER_PRINT );
        
        
        ResultMatcher responseMatcher = content().string( equalTo("String in Zelle A1: \"abc def\"") );
        ra1.andExpect(responseMatcher);
    }

}
