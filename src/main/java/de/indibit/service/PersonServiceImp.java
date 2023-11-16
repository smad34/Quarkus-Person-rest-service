package de.indibit.service;

import de.indibit.domain.Person;
import de.indibit.entity.PersonEntity;
import de.indibit.exception.EntityNotFoundException;
import de.indibit.mapping.PersonMapper;
import de.indibit.repository.PersonRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.codehaus.groovy.tools.shell.util.MessageSource;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PersonServiceImp implements PersonService {

    PersonMapper personMapper;
    MessageSource messageSource;

    @Inject
    PersonRepository repository;
    @Override
    public List<Person> getPersons() {
        List<PersonEntity> personEntity = repository.findAll();
      return  null;
    }

    @Override
    public boolean createOrUpdatePerson(Person person) {
        PersonEntity personEntity = personMapper.toPerson(person);
        repository.persist(personEntity);
        return repository.isPersistent(personEntity);
    }

    @Override
    public Person findById(Long id) {
        Optional<PersonEntity> personOptional = Optional.ofNullable(repository.findById(id));

        return personOptional
                .map(personMapper::fromPerson)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("person.notfound")));
    }

    @Override
    public boolean remove(Long id) {
        return repository.deleteById(id);
    }
}
