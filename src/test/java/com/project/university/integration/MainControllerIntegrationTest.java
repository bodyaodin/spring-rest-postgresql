package com.project.university.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class MainControllerIntegrationTest extends IntegrationTest {

    @Test
    public void shouldReturnStatusOkAndString() {
        when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .body(containsString("Welcome to University DB!"));
    }
}
