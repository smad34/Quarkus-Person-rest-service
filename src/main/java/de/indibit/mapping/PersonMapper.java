package de.indibit.mapping;

import de.indibit.domain.Person;
import de.indibit.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    Person fromPerson(PersonEntity personEntity);

    PersonEntity toPerson(Person person);

}
