package de.indibit.test.service;
import de.indibit.entity.Car;
import de.indibit.entity.Person;
import de.indibit.repository.CarRepository;
import de.indibit.repository.PersonRepository;
import de.indibit.service.LocalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    @InjectMocks
    private LocalService service;

    @Mock
    private PersonRepository personRepository;
    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNullPlateWhenEvaluateThenError() {
        // Arrange
        Person person = new Person();
        person.setAge(30);
        person.setFirstName("Ali");
        person.setLastName("Hoseini");

        // Act
        when(personRepository.findById(person.getId())).thenReturn(person);
        Person retrievedPerson = service.findById(person.getId());

        // Assert
        assertEquals(person.getAge(), retrievedPerson.getAge());
        assertNotEquals(person.getAge(), 20);
        assertEquals(person.getFirstName(), retrievedPerson.getFirstName());
        assertEquals(person.getLastName(), retrievedPerson.getLastName());
    }
    @Test
    void givenNonExistentIdWhenFindByIdThenReturnNull() {
        // Arrange
        long nonexistentId = 999L;

        // Act
        when(personRepository.findById(nonexistentId)).thenReturn(null);
        Person retrievedPerson = service.findById(nonexistentId);

        // Assert
        assertNull(retrievedPerson);
    }

    @Test
    void givenExistingCarIdWhenFindCarByIdThenReturnCar() {
        // Arrange
        long carId = 1L;
        Car expectedCar = new Car();
        expectedCar.id = carId;
        expectedCar.model = "SUV";

        // Act
        when(carRepository.findById(carId)).thenReturn(expectedCar);
        Optional<Car>  retrievedCar = service.findCarById(carId);

        // Assert
        assertEquals(expectedCar, retrievedCar.orElse(null), "Unexpected car retrieved");
    }
}