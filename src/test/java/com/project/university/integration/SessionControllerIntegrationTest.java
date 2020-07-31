package com.project.university.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.equalTo;

public class SessionControllerIntegrationTest extends IntegrationTest{

    private static final String BASE_PATH = "/sessions";

    @Test
    public void shouldReturnListOfSessions() {
        given()
                    .basePath(BASE_PATH)
                .when()
                    .get()
                .then()
                    .contentType(ContentType.JSON)
                    .body("size()", equalTo(2))
                    .root("find{it.id == %s}", withArgs(1))
                    .body("id", equalTo(1))
                    .body("examDate", equalTo("2020-01-01"))
                    .body("semester", equalTo(1))
                    .body("student.id", equalTo(1))
                    .body("subject.id", equalTo(1))
                    .body("grade", equalTo(5))
                .log().body();
    }

    @Test
    public void shouldReturnOneSessionById() {
        String path = "/get/{id}";

        given()
                    .basePath((BASE_PATH))
                    .pathParam("id", 1)
                .when()
                    .get(path)
                .then()
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(1))
                    .body("examDate", equalTo("2020-01-01"))
                    .body("semester", equalTo(1))
                    .body("student.id", equalTo(1))
                    .body("subject.id", equalTo(1))
                    .body("grade", equalTo(5))
                .log().body();
    }

    @Test
    public void shouldThrowExceptionWhenSessionIdNotFound() {
        String path = "/get/{id}";

        given()
                    .basePath((BASE_PATH))
                    .pathParam("id", 3)
                .when()
                    .get(path)
                .then()
                    .statusCode(HttpStatus.NOT_FOUND.value())
                .log().body();
    }

    @Test
    public void shouldAddNewSession() {
        String path = "/add";
        String filePath = "src/test/resources/request/new_session.json";

        given()
                    .basePath((BASE_PATH))
                    .contentType(ContentType.JSON)
                    .body(new File(filePath))
                .when()
                    .post(path)
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(3))
                    .body("examDate", equalTo("2020-01-01"))
                    .body("semester", equalTo(1))
                    .body("student.id", equalTo(1))
                    .body("subject.id", equalTo(1))
                    .body("grade", equalTo(3))
                .log().body();
    }

    @Test
    public void shouldUpdateSession() {
        String path = "/update";
        String filePath = "src/test/resources/request/update_session.json";

        given()
                    .basePath((BASE_PATH))
                    .contentType(ContentType.JSON)
                    .body(new File(filePath))
                .when()
                    .put(path)
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void shouldDeleteSessionById() {
        String path = "/delete/{id}";

        given()
                    .basePath((BASE_PATH))
                    .pathParam("id", 2)
                .when()
                    .delete(path)
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
