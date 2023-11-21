package de.indibit.itest;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.indibit.entity.Person;

import de.indibit.utils.Utils;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
@Slf4j
public class EndPointITest {
    private Person savedPerson;

    @BeforeEach
    void setUp() {
        savedPerson = new Person();
        savedPerson.setFirstName("Andro");
        savedPerson.setLastName("Jack");
        savedPerson.setAge(30);
    }

    @Test
    void shouldSaveNewPerson() throws JsonProcessingException {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Utils.convertToJson(savedPerson))
                .when()
                .post("/persons")
                .then()
                .log().status()
                .statusCode(201);
    }

    @Test
    void shouldUpdateUnavailablePerson() throws JsonProcessingException {
        savedPerson.setId(11251L);
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Utils.convertToJson(savedPerson))
                .when()
                .put("/persons/{id}", savedPerson.getId())
                .then()
                .log().status()
                .statusCode(404);
    }

    @Test
    @Transactional
    public void shouldDeletePersonEndpoint() {
        savedPerson.setId(1234L);
        given()
                .pathParam("id", savedPerson.getId())
                .when().get("/persons/{id}")
                .then()
                .statusCode(404); // Not Found
    }

    @Test
    @Transactional
    public void shouldAddAndDeletePerson() throws JsonProcessingException {
        // Add a person

        String locationHeader = given()
                .contentType(MediaType.APPLICATION_JSON)
                // .body("{\"firstName\":\"John\", \"age\":30}")
                .body(Utils.convertToJson(savedPerson))
                .when().post("/persons")
                .then()
                .statusCode(201) // Created
                .extract().header("Location");
        Long personId = Utils.extractIdFromLocationHeader(locationHeader);


        // Verify that the person was added
        given()
                .pathParam("id", personId)
                .when().get("/persons/{id}")
                .then()
                .statusCode(200)
                .body("firstName", is("Andro"))
                .body("age", is(30));


        // Delete the person
        given()
                .pathParam("id", personId)
                .when().delete("/persons/byId/{id}")
                .then()
                .statusCode(200); // Accepted

        // Verify that the person was deleted
        given()
                .pathParam("id", personId)
                .when().get("/persons/{id}")
                .then()
                .statusCode(404); // Not Found
    }


}
