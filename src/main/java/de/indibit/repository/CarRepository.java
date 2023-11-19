package de.indibit.repository;

import de.indibit.entity.CarEntity;
import de.indibit.entity.PersonEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

/**
 * <b>Title:</b> PersonRepository <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate interface for {@link PersonEntity}.<br>
 * <br>
 * This class serves as a Hibernate interface for managing {@link PersonEntity} instances.
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@ApplicationScoped
public class CarRepository implements PanacheRepository<CarEntity> {

}
