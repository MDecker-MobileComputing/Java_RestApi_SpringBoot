/**
 * Unit-Tests für die REST-Methoden. Die Klassen mit den Unit-Test-Methoden müssen
 * mit {@code @SpringBootTest} annotiert werden, und jede der Test-Methoden mit
 * {@code @Test}.
 * <br><br>
 *
 * Damit die JavaDoc-Dateien für die Unit-Tests erzeugt werden, muss der Befehl
 * {@code mvn javadoc:test-javadoc} ausgeführt werden. Die Doku wird dann in
 * das Verzeichnis {@code target/site/testapidocs} geschrieben.
 * <br><br> 
 * Doku zum Unit-Testen von Spring-REST-Methoden, siehe auch
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
 * Es wird JUnit 4.x verwendet, siehe auch
 * <a href="https://junit.org/junit4/javadoc/latest/" target="_blank">die zugehörige API-Doku</a>
 * (in Ausgabe von Befehl {@code mvn dependency:tree} kann man sehen, welche Version von JUnit verwendet wird).
 * <br><br>
 *
 * Es werden Matcher aus dem <a href="http://hamcrest.org/JavaHamcrest/" target="_blank">Hamcrest-Framework</a> verwendet
 * ("Hamcrest" ist ein Anagramm des Wort "Matchers").
 * Siehe auch die <a href="http://hamcrest.org/JavaHamcrest/javadoc/1.3/" target="_blank">API-Doku von Hamcrest 1.3</a>
 * (in Ausgabe von Befehl {@code mvn dependency:tree} kann man sehen, welche Version von Hamcrest verwendet wird).
 * <br><br><br>
 * 
 * 
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
package de.mide.restapidemo;
