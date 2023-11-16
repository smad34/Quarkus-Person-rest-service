package de.indibit.person;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class PersonServiceImp implements PersonService {
    @Inject
    PersonRepository repository;
    @Override
    public List<PersonEntity> getPersons() {
        return repository.listAll();
    }

    @Override
    public boolean createOrUpdatePerson(PersonEntity person) {
        repository.persist(person);
        return repository.isPersistent(person);
    }
    @Override
    public PersonEntity findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean remove(Long id) {
        return repository.deleteById(id);
    }
}
