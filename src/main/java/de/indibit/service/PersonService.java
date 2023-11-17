package de.indibit.service;

import de.indibit.domain.Person;
import de.indibit.entity.PersonEntity;

import de.indibit.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;

@ApplicationScoped
public class PersonService {
    @Inject
    PersonRepository repository;

    public List<Person> getPersons() {
        return repository.listAll().stream()
                .map(PersonEntity::toDomain)
                .collect(Collectors.toList());
    }

    public boolean createOrUpdatePerson(Person person) {
        PersonEntity mergedPerson = person.mergeDomain();
        if (mergedPerson.id != null)
            getEntityManager().merge(mergedPerson);
        else
            repository.persist(mergedPerson);
        return repository.isPersistent(mergedPerson);
    }

    public Person findById(Long id) {
        PersonEntity personEntity = repository.findById(id);
        return personEntity != null ? personEntity.toDomain() : null;
    }

    public boolean remove(Long id) {
        return repository.deleteById(id);
    }
}
