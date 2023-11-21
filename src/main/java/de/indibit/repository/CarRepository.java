package de.indibit.repository;

import de.indibit.entity.Car;
import de.indibit.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

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
public class CarRepository implements PanacheRepository<Car> {
}
