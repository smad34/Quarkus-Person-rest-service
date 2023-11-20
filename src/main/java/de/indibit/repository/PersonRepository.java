package de.indibit.repository;

import de.indibit.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <b>Title:</b> PersonRepository <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate interface for {@link Person}.<br>
 * <br>
 * This class serves as a Hibernate interface for managing {@link Person} instances.
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {
    /**
     * list Persons Ordered By firsName.
     *
     * @return list of persons order by firstName.
     */
    public List<Person> listPersonsOrderedByFirstName() {
        return this.listAll(Sort.by("firstName").ascending());
    }

    /**
     * find By FirstName.
     *
     * @param firstName identifies vehicle
     * @return available entity
     */
    public Optional<Person> findByFirstName(String firstName) {
        return this.find("firstName", firstName).firstResultOptional();
    }

    /**
     * delete person By Name.
     *
     * @param firstName identifies vehicle
     * @return {@code true} if a person with the given first name was found and successfully deleted,
     * {@code false} if no matching person was found.
     */
    public boolean deleteByName(String firstName) {
        Optional<Person> personEntity = this.findByFirstName(firstName);
        if (personEntity.isPresent()) {
            this.delete(personEntity.get());
            return true;
        } else return false;

    }


    public List<Person> findAllWithCars() {
        return findAll().stream().toList();
    }
}
