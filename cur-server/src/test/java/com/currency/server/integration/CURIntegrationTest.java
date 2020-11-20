package com.currency.server.integration;

import com.currency.server.CURServerApplication;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(classes = CURServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("local")
public class CURIntegrationTest {

    @Test
    public void testGetCountryInfo(){
        given()
                .contentType(ContentType.JSON)
                .param("currency","eur")
                .when()
                .get("/borderInfo")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(containsString(("{\"name\":\"Åland Islands\",\"topLevelDomain\":[\".ax\"],\"alpha2Code\":\"AX\",\"alpha3Code\":\"ALA\",\"callingCodes\":[\"358\"],\"capital\":\"Mariehamn\",\"altSpellings\":")));
    }
}

