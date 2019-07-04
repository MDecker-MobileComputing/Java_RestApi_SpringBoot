package de.mide.restapidemo;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;

import de.mide.restapidemo.rest2.ParameterRestController;


/**
 * Unit-Test-Methoden für die REST-Methoden in Klasse {@link ParameterRestController}.
 * <br><br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParameterRestControllerTests {
        
    /** 
     * Mock-Objekt für die Ausführung der HTTP-Requests.
     * Es werden HTTP-Requests zu REST-Methoden durch Aufruf der Methode {@link MockMvc#perform(RequestBuilder)}
     * simuliert. 
     */
    @Autowired
    private MockMvc _mock;
    
    
    /**
     * Test-Methode für die REST-Methode {@code rest2/addieren}; diese REST-Methode erwartet zwei URL-Parameter.
     * 
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testAddieren() throws Exception {
        
        RequestBuilder requestBuilder       = get("/rest2/addieren?zahl1=10&zahl2=20"); // 10 + 20 = 30
        ResultHandler  printHandler         = print();
        ResultMatcher  httpStatus200Matcher = status().isOk();  
                                
        ResultActions ra1 = _mock.perform( requestBuilder ).andDo( printHandler ).andExpect( httpStatus200Matcher );    
        
        ResultMatcher responseMatcher = content().string( equalTo("Summe=30") );
        ra1.andExpect(responseMatcher);        
    }

}
