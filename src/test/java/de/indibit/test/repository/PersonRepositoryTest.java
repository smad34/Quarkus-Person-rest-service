package de.indibit.test.repository;

import de.indibit.MariaDbProfile;
import de.indibit.entity.Person;
import de.indibit.repository.PersonRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestProfile(MariaDbProfile.class)
public class PersonRepositoryTest {

    @Inject
    PersonRepository personRepository;

    private Person savedPerson;

    @BeforeEach
    void setUp() {
        savedPerson = new Person();
        savedPerson.setFirstName("Andro");
        savedPerson.setLastName("Jack");
        savedPerson.setAge(30);
    }

    @Test
    @Transactional
    void shouldFindPersonById() {
        personRepository.persist(savedPerson);

        Person foundPerson = personRepository.findById(savedPerson.getId());
        assertNotNull(foundPerson, "Person should not be null");
        assertPersonEquals(savedPerson, foundPerson);
    }

    @Test
    @Transactional
    void shouldFindByFirstName() {
        personRepository.persist(savedPerson);

        Person foundPerson = personRepository.findByFirstName("Andro").orElse(null);
        assertNotNull(foundPerson, "Person should not be null");
        assertPersonEquals(savedPerson, foundPerson);
    }

    private void assertPersonEquals(Person expected, Person actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getAge(), actual.getAge());
    }
}