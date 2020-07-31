package com.project.university.integration;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/queries/InsertDBQueries.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "/queries/DeleteDBQueries.sql", executionPhase = AFTER_TEST_METHOD)
})
public class IntegrationTest {

    protected static final String EMAIL = "email@gmail.com";
    protected static final String PASSWORD = "23622";

    @LocalServerPort
    protected int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(EMAIL);
        authScheme.setPassword(PASSWORD);
        RestAssured.authentication = authScheme;
    }
}