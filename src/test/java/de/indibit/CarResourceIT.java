package de.indibit;

import de.indibit.entity.Person;

import de.indibit.service.LocalService;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@TestProfile(MariaDbProfile.class)
public class CarResourceIT {
    @Inject
    LocalService service;

    @Test
    @Transactional
    void testFindPersonById() {
        // Arrange
        Person savedPerson = new Person();
        savedPerson.setFirstName("John");
        savedPerson.setLastName("Doe");
        savedPerson.setAge(30);


        service.createOrUpdatePerson(savedPerson);

        // Act/Assert using RestAssured for integration testing
        given()
                .when().get("/persons")
                .then()
                .statusCode(200)
                .body("$", hasSize(1),
                        "[0].id", equalTo(1), // Adjust this if necessary
                        "[0].firstName", equalTo("John"),
                        "[0].lastName", equalTo("Doe"));
    }

}
