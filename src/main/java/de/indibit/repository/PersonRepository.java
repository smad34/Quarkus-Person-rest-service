package de.indibit.repository;

import de.indibit.entity.PersonEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<PersonEntity> {
}
