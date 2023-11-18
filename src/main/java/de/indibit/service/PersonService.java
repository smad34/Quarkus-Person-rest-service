package de.indibit.service;

import de.indibit.domain.Person;
import de.indibit.entity.PersonEntity;
import de.indibit.repository.PersonRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Person> getPersons() {
        return repository.listAll().stream()
                .map(PersonEntity::toDomain)
                .collect(Collectors.toList());
    }

    public boolean createOrUpdatePerson(Person person) {
        PersonEntity mergedPerson = person.mergeDomain();
        if (mergedPerson.getId() == null)
            repository.persist(mergedPerson);
        else
            repository.getEntityManager().merge(person.mergeDomain());
        return repository.isPersistent(mergedPerson);
    }

    public Person findById(Long id) {
        PersonEntity personEntity = repository.findById(id);
        return personEntity != null ? personEntity.toDomain() : null;
    }

    public boolean remove(Long id) {
        return repository.deleteById(id);
    }

    public boolean deleteByName(String firstName) {
        return repository.deleteByName(firstName);
    }
}