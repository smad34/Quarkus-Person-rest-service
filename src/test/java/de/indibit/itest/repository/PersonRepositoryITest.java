package de.indibit.itest.repository;

import de.indibit.entity.Person;

import de.indibit.service.LocalService;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@Slf4j
public class PersonRepositoryITest {
    @Inject
    LocalService service;

    private Person savedPerson;

    @BeforeEach
    void setUp() {
        savedPerson = new Person();
        savedPerson.setFirstName("John222");
        savedPerson.setLastName("Doe222");
        savedPerson.setAge(30);
    }

    @Test
    void testSaveNewPersonIT() {
        given()
                .contentType(ContentType.JSON)
                .body(savedPerson)
                .when()
                .post("/persons")
                .then()
                .log().status()
                .statusCode(201);
    }

    @Test
    void testUpdateUnavailablePersonIT() {
        savedPerson.setId(11251L);
        given()
                .contentType(ContentType.JSON)
                .body(savedPerson)
                .when()
                .put("/persons/{id}", savedPerson.getId())
                .then()
                .log().status()
                .statusCode(404);
    }

    @Test
    @Transactional
    void    testRetrieveSavePersonIT() {
        service.createOrUpdatePerson(savedPerson);
        given()
                .when().get("/persons/" + 2251)
                .then()
                .log().body()
                .statusCode(200);
    }

}
