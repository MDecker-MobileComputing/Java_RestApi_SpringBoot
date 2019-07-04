package de.mide.restapidemo;

import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import de.mide.restapidemo.rest1.DatumUndZeitRestController;


/**
 * Unit-Test-Methoden für die REST-Methoden in Klasse {@link DatumUndZeitRestController}.
 * <br><br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DatumUndZeitRestControllerTests {
    
    /** 
     * Mock-Objekt für die Ausführung der HTTP-Requests.
     * Es werden HTTP-Requests zu REST-Methoden durch Aufruf der Methode {@link MockMvc#perform(RequestBuilder)}
     * simuliert. 
     */
    @Autowired
    private MockMvc _mock;
    
    
    /**
     * Test für REST-Methode {@code rest1/datumUndZeitAlsObjekt}.
     * <br><br>
     * 
     * Die HTTP-Response wird mit {@code .andDo(printHandler)} auf die Konsole geschrieben, was
     * einen Output ähnlich dem folgenden ergibt:
     * <pre>
     *   MockHttpServletResponse:
     *           Status = 200
     *    Error message = null
     *          Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"29"]
     *     Content type = text/plain;charset=UTF-8
     *             Body = Thu Jul 04 14:28:12 CEST 2019
     *    Forwarded URL = null
     *   Redirected URL = null
     *          Cookies = []
     * </pre>
     * 
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDatumUndZeitAlsString() throws Exception {
                                         
        RequestBuilder requestBuilder       = get("/rest1/datumUndZeitAlsString");
        ResultHandler  printHandler         = print();
        ResultMatcher  httpStatus200Matcher = status().isOk();
        
        MvcResult mvcResult = _mock.perform( requestBuilder ).andDo( printHandler ).andExpect( httpStatus200Matcher ).andReturn();
                
        String httpResponseAsString = mvcResult.getResponse().getContentAsString();
        int    httpResponseLaenge   = httpResponseAsString.trim().length(); 
        
        assertTrue("String mit Datum+Uhrzeit zu kurz.", httpResponseLaenge > 1);               
    }
    
    
    /**
     * Test für REST-Methode {@code rest1/datumUndZeitAlsObjekt}, die JSON-String zurückgibt.
     * <br><br>  
     * 
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDatumUndZeitAlsObjekt() throws Exception {

        RequestBuilder requestBuilder       = get("/rest1/datumUndZeitAlsObjekt");
        ResultHandler  printHandler         = print();
        ResultMatcher  httpStatus200Matcher = status().isOk();  
                                
        ResultActions ra1 = _mock.perform( requestBuilder ).andDo( printHandler ).andExpect( httpStatus200Matcher );

        
        ResultMatcher jsonAnfangMatcher = content().string( startsWith("{") );
        ResultMatcher jsonEndeMatcher   = content().string(   endsWith("}") );                
        
        ResultActions ra2 = ra1.andExpect(jsonAnfangMatcher).andExpect(jsonEndeMatcher);
        
        
        ResultMatcher jsonKeyDatumMatcher = content().string( containsString( "\"datum\"" ) );
        ResultMatcher jsonKeyZeitMatcher  = content().string( containsString( "\"zeit\""  ) );
        
        ra2.andExpect(jsonKeyDatumMatcher).andExpect(jsonKeyZeitMatcher);                
    }
    
    
    /**
     * Test für REST-Methode {@code /rest1/datumUndZeitMitResponseEntity}.
     * Überprüft u.a. die Custom Response Header.
     * 
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDatumUndZeitMitResponseEntity() throws Exception {
        
        RequestBuilder requestBuilder       = get("/rest1/datumUndZeitMitResponseEntity");
        ResultHandler  printHandler         = print();
        ResultMatcher  httpStatus202Matcher = status().isAccepted(); // nicht 200!
                                
        ResultActions ra1 = _mock.perform( requestBuilder ).andDo( printHandler ).andExpect( httpStatus202Matcher );
        
        ResultMatcher serverHeaderMatcher = header().string("Server"       , "Spring Boot RestController");
        ResultMatcher cacheHeaderMatcher  = header().string("Cache-Control", "no-cache"                  );
        
        MvcResult mvcResult = ra1.andExpect(serverHeaderMatcher).andExpect(cacheHeaderMatcher).andReturn();
        
        String httpResponseAsString = mvcResult.getResponse().getContentAsString();
        int    httpResponseLaenge   = httpResponseAsString.trim().length(); 
        
        assertTrue("String mit Datum+Uhrzeit zu kurz.", httpResponseLaenge > 1);        
    }

}
