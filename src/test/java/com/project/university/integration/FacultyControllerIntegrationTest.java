package com.project.university.integration;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FacultyControllerIntegrationTest extends IntegrationTest{

    private static final String BASE_PATH = "/faculties";

    @Test
    public void shouldReturnListOfFaculties() {
        given()
                    .basePath(BASE_PATH)
                .when()
                    .get()
                .then()
                    .contentType(ContentType.JSON)
                    .body("size()", equalTo(2))
                    .root("find{it.id == %s}", withArgs(1))
                    .body("id", equalTo(1))
                    .body("facultyName", equalTo("faculty"))
                .log().body();
    }

    @Test
    public void shouldReturnOneFacultyById() {
        String path = "/get/{id}";

        given()
                    .basePath((BASE_PATH))
                    .pathParam("id", 1)
                .when()
                    .get(path)
                .then()
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(1))
                    .body("facultyName", equalTo("faculty"))
                .log().body();
    }

    @Test
    public void shouldThrowExceptionWhenFacultyIdNotFound() {
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
    public void shouldAddNewFaculty() {
        String path = "/add";
        String filePath = "src/test/resources/request/new_faculty.json";

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
                    .body("facultyName", equalTo("new faculty"))
                .log().body();
    }

    @Test
    public void shouldUpdateFaculty() {
        String path = "/update";
        String filePath = "src/test/resources/request/update_faculty.json";

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
    public void shouldDeleteFacultyById() {
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
