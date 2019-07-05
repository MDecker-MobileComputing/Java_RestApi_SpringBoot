package de.mide.restapidemo;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
     * Test-Methode für die REST-Methode {@code rest3/datenViaPostRequestEmpfangen} via POST-Request.
     *
     * @throws Exception  Fehler beim Testen aufgetreten
     */
    @Test
    public void testDatenViaPostRequestEmpfangen() throws Exception {

        final String textPayload = "Lorem Ipsum"; // 11 Zeichen

        ResultHandler  printHandler         = print();
        ResultMatcher  httpStatus200Matcher = status().isOk();

        RequestBuilder requestBuilder = post("/rest3/datenViaPostRequestEmpfangen").contentType(MediaType.TEXT_PLAIN).content(textPayload);

        ResultActions ra1 = _mock.perform( requestBuilder ).andDo( printHandler ).andExpect( httpStatus200Matcher );

        ResultMatcher responseMatcher = content().string( equalTo("Daten (11 Zeichen) empfangen.") );
        ra1.andExpect(responseMatcher);
    }

}
