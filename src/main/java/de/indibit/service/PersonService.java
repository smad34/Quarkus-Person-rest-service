package de.indibit.service;

import de.indibit.domain.Person;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

public interface PersonService {
    PanacheQuery<Person> getPersons();
    boolean createOrUpdatePerson(Person person);
    Person findById(Long id);
    boolean remove(Long id);
}
