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

    private Person samplePerson;
    private Car sampleCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        samplePerson = new Person();
        samplePerson.setFirstName("Andro");
        samplePerson.setLastName("Jack");
        samplePerson.setAge(30);

        sampleCar = new Car();
        sampleCar.setModel("SUV");;
    }
    @Test
    void givenValidPersonIdWhenFindByIdThenReturnPerson() {
        when(personRepository.findById(samplePerson.getId())).thenReturn(samplePerson);
        Person retrievedPerson = service.findById(samplePerson.getId());

        assertEquals(samplePerson.getAge(), retrievedPerson.getAge());
        assertNotEquals(samplePerson.getAge(), 20);
        assertEquals(samplePerson.getFirstName(), retrievedPerson.getFirstName());
        assertEquals(samplePerson.getLastName(), retrievedPerson.getLastName());
    }

    @Test
    void givenNonExistentPersonIdWhenFindByIdThenReturnNull() {
        samplePerson.setId(999L);

        when(personRepository.findById(samplePerson.getId())).thenReturn(null);
        Person retrievedPerson = service.findById(samplePerson.getId());

        assertNull(retrievedPerson);
    }

    @Test
    void givenExistingCarIdWhenFindCarByIdThenReturnCar() {
        sampleCar.setId(1L);

        when(carRepository.findById(sampleCar.getId())).thenReturn(sampleCar);
        Optional<Car>  retrievedCar = service.findCarById(sampleCar.getId());

        assertEquals(sampleCar, retrievedCar.orElse(null), "Unexpected car retrieved");
    }
}