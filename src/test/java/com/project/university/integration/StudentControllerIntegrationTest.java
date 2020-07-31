package com.project.university.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.equalTo;

public class StudentControllerIntegrationTest extends IntegrationTest{

    private static final String BASE_PATH = "/students";

    @Test
    public void shouldReturnListOfStudents() {
        given()
                    .basePath(BASE_PATH)
                .when()
                    .get()
                .then()
                    .contentType(ContentType.JSON)
                    .body("size()", equalTo(2))
                    .root("find{it.id == %s}", withArgs(1))
                    .body("id", equalTo(1))
                    .body("firstName", equalTo("fname"))
                    .body("lastName", equalTo("lname"))
                    .body("email", equalTo("email@gmail.com"))
                    .body("phone", equalTo("911"))
                    .body("birthDate", equalTo("2002-12-29"))
                    .body("reportCard", equalTo(23622))
                    .body("course", equalTo(1))
                    .body("entryDate", equalTo("2020-01-01"))
                    .body("studentGroup.id", equalTo(1))
                .log().body();
    }

    @Test
    public void shouldReturnOneStudentById() {
        String path = "/get/{id}";

        given()
                    .basePath((BASE_PATH))
                    .pathParam("id", 1)
                .when()
                    .get(path)
                .then()
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(1))
                    .body("firstName", equalTo("fname"))
                    .body("lastName", equalTo("lname"))
                    .body("email", equalTo("email@gmail.com"))
                    .body("phone", equalTo("911"))
                    .body("birthDate", equalTo("2002-12-29"))
                    .body("reportCard", equalTo(23622))
                    .body("course", equalTo(1))
                    .body("entryDate", equalTo("2020-01-01"))
                    .body("studentGroup.id", equalTo(1))
                .log().body();
    }

    @Test
    public void shouldThrowExceptionWhenStudentIdNotFound() {
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
    public void shouldAddNewStudent() {
        String path = "/add";
        String filePath = "src/test/resources/request/new_student.json";

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
                    .body("firstName", equalTo("new fname"))
                    .body("lastName", equalTo("new lname"))
                    .body("email", equalTo("email3@gmail.com"))
                    .body("phone", equalTo("101"))
                    .body("birthDate", equalTo("2002-12-29"))
                    .body("reportCard", equalTo(23625))
                    .body("course", equalTo(1))
                    .body("entryDate", equalTo("2020-01-01"))
                    .body("studentGroup.id", equalTo(1))
                .log().body();
    }

    @Test
    public void shouldUpdateStudent() {
        String path = "/update";
        String filePath = "src/test/resources/request/update_student.json";

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
    public void shouldDeleteChairById() {
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
