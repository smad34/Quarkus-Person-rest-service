package de.indibit.service;

import de.indibit.entity.Car;
import de.indibit.entity.Person;
import de.indibit.repository.CarRepository;
import de.indibit.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 * <b>Title:</b> PersonService <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> the Service for person<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository repository;
    @Inject
    CarRepository carRepository;

    public List<Person> getPersons() {
        return repository.listAll();
    }

    public List<Person> findAllWithCars() {
        List<Person> person = repository.listAll();
        return person ;
    }

    public boolean createOrUpdatePerson(Person person) {
        if (person.getId() == null)
            repository.persist(person);
        else
            repository.getEntityManager().merge(person);
        if(person.getCars()!=null){
            for(Car car: person.getCars()){
                car.person = person;
                carRepository.persist(car);
            }
        }
        return repository.isPersistent(person);
    }

    public Person findById(Long id) {
        return repository.findById(id);
    }

    public boolean remove(Long id) {
        return repository.deleteById(id);
    }

    public boolean deleteByName(String firstName) {
        return repository.deleteByName(firstName);
    }
}