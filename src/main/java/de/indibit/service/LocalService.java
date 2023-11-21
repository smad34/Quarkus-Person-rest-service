package de.indibit.service;

import de.indibit.entity.Car;
import de.indibit.entity.Person;
import de.indibit.repository.CarRepository;
import de.indibit.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

/**
 * <b>Title:</b> LocalService <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> the Service for person<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@ApplicationScoped
public class LocalService {

    @Inject
    PersonRepository personRepository;
    @Inject
    CarRepository carRepository;

    /**
     * find all cars persons with related cars
     *
     * @return the list of persons with related cars
     */
    public List<Person> findAllWithCars() {
        return personRepository.listAll();
    }

    /**
     * Creates or updates a person along with associated cars in the database.
     * If the person is new (with a null id), it is persisted along with its cars.
     * If the person already exists (with a non-null id), any changes to the person and its cars are synchronized with the database.
     *
     * @param person The person object to be created or updated, including associated cars.
     * @return True if the person is successfully created or updated in the database; false otherwise.
     */
    public boolean createOrUpdatePerson(Person person) {
        if (person.getCars() != null) {
            for (Car car : person.getCars()) {
                car.person = person;
                if (car.getId() == null)
                    carRepository.persist(car);
                else
                    carRepository.getEntityManager().merge(car);
            }
        }
        if (person.getId() == null)
            personRepository.persist(person);
        else
            personRepository.getEntityManager().merge(person);
        return personRepository.isPersistent(person);
    }

    /**
     * Find a person by id.
     *
     * @param id The person object to search,
     * @return the target person object.
     */
    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    /**
     * delete a person by name.
     *
     * @param id The person object to be deleted.
     * @return result status.
     */
    public boolean remove(Long id) {
        return personRepository.deleteById(id);
    }

    /**
     * delete a person by name.
     *
     * @param firstName The person object to be deleted.
     * @return result status.
     */
    public boolean deleteByName(String firstName) {
        return personRepository.deleteByName(firstName);
    }

    /**
     * find a car by id.
     *
     * @param id of car to find it.
     * @return car object.
     */
    public Optional<Car> findCarById(Long id) {
        return Optional.ofNullable(carRepository.findById(id));
    }

}