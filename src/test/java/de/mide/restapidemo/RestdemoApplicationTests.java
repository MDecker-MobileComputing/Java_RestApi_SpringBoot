package de.mide.restapidemo;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringContains.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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


/**
 * Doku zum Unit-Testen von REST-Methoden siehe
 * <a href="https://spring.io/guides/gs/testing-web/" target="_blank">diesen Guide auf <i>spring.io</i></a>.
 * <br><br>
 * 
 * Nur Tests ausführen ("test"` ist eine Phase von maven): {@code mvn test}<br>
 * Die Tests werden aber auch bei {@code mvn package} ausgeführt; wenn man beim Packagen die Tests überspringen
 * will, dann ruft man {@code mvn -Dmaven.test.skip=true package} auf.
 * <br><br>
 * 
 * Die Appliation ist keine MVC-Anwendung (sie verwendet nicht {@code @WebMvcTest} sondern {@code @SpringBootTest},
 * deshalb muss die Testklasse zusätzlich mit {@code @AutoConfigureMockMvc} annotiert werden. 
 * (<a href="https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests" target="_blank">Quelle</a>)
 * <br><br>
 *
 * Es werden Matcher aus dem <a href="http://hamcrest.org/JavaHamcrest/" target="_blank">Hamcrest-Framework</a> verwendet
 * ("Hamcrest" ist ein Anagramm des Wort "Matchers").
 * Siehe auch die <a href="http://hamcrest.org/JavaHamcrest/javadoc/1.3/" target="_blank">API-Doku von Hamcrest 1.3</a>
 * (durch Ausgabe von Befehl {@code mvn dependency:tree} kann man sehen, welche Version von Hamcrest verwendet wird).
 * <br><br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestdemoApplicationTests {
    
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
     * MockHttpServletResponse:
     *         Status = 200
     *  Error message = null
     *        Headers = [Content-Type:"text/plain;charset=UTF-8", Content-Length:"29"]
     *   Content type = text/plain;charset=UTF-8
     *           Body = Thu Jul 04 14:28:12 CEST 2019
     *  Forwarded URL = null
     * Redirected URL = null
     *        Cookies = []
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

}
