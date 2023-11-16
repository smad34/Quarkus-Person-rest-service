package de.indibit.person;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
public interface PersonService {
    List<PersonEntity> getPersons();
    boolean createOrUpdatePerson(PersonEntity person);
    PersonEntity findById(Long id);
    boolean remove(Long id);
}
