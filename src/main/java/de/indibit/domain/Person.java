package de.indibit.domain;

import de.indibit.entity.PersonEntity;
import lombok.*;
import java.io.Serializable;

/**
 * <b>Title:</b> Person <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b>Internal domain model.<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    public Long id;
    public String firstName;
    public String lastName;
    public int age;

    public PersonEntity mergeDomain() {
        return new PersonEntity(this.id, this.firstName, this.lastName, this.age);
    }
}
