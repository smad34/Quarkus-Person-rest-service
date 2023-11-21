package de.indibit.repository;

import de.indibit.MariaDbProfile;
import de.indibit.entity.Person;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static io.smallrye.common.constraint.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestProfile(MariaDbProfile.class)
public class PersonRepositoryTest {
    @Inject
    PersonRepository personRepository;

    @Test
    @Transactional
    void testFindPersonById() {
        // Arrange
        Person savedPerson = new Person();
        savedPerson.setFirstName("John");
        savedPerson.setLastName("Doe");
        savedPerson.setAge(30);
        personRepository.persist(savedPerson);

        // Act
        Person foundPerson = personRepository.findById(savedPerson.getId());

        // Assert
        assertEquals(savedPerson.getFirstName(), foundPerson.getFirstName());
        assertEquals(savedPerson.getLastName(), foundPerson.getLastName());
        assertEquals(savedPerson.getAge(), foundPerson.getAge());
    }

    @Test
    @Transactional
    public void testFindByFirstName() {
        // Arrange
        Person savedPerson = new Person();
        savedPerson.setFirstName("Alice");
        savedPerson.setLastName("Wonderland");
        savedPerson.setAge(25);
        personRepository.persist(savedPerson);

        // Act
        Person foundPerson = personRepository.findByFirstName("Alice").orElse(null);

        // Assert
        assertTrue(foundPerson != null);
        assert foundPerson != null;
        assertEquals(savedPerson.getFirstName(), foundPerson.getFirstName());
        assertEquals(savedPerson.getLastName(), foundPerson.getLastName());
        assertEquals(savedPerson.getAge(), foundPerson.getAge());
    }
}
