package com.project.university.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.equalTo;

public class SubjectControllerIntegrationTest extends IntegrationTest{

    private static final String BASE_PATH = "/subjects";

    @Test
    public void shouldReturnListOfSubjects() {
        given()
                    .basePath(BASE_PATH)
                .when()
                    .get()
                .then()
                    .contentType(ContentType.JSON)
                    .body("size()", equalTo(2))
                    .root("find{it.id == %s}", withArgs(1))
                    .body("id", equalTo(1))
                    .body("subjectName", equalTo("subject"))
                .log().body();
    }

    @Test
    public void shouldReturnOneSubjectById() {
        String path = "/get/{id}";

        given()
                    .basePath((BASE_PATH))
                    .pathParam("id", 1)
                .when()
                    .get(path)
                .then()
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(1))
                    .body("subjectName", equalTo("subject"))
                .log().body();
    }

    @Test
    public void shouldThrowExceptionWhenSubjectIdNotFound() {
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
    public void shouldAddNewSubject() {
        String path = "/add";
        String filePath = "src/test/resources/request/new_subject.json";

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
                    .body("subjectName", equalTo("new subject"))
                .log().body();
    }

    @Test
    public void shouldUpdateSubject() {
        String path = "/update";
        String filePath = "src/test/resources/request/update_subject.json";

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
    public void shouldDeleteSubjectById() {
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
