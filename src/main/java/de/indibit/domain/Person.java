package de.indibit.domain;

import de.indibit.entity.PersonEntity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public PersonEntity mergeDomain(){
        return new PersonEntity(this.id,this.firstName,this.lastName,this.age);
    }
}
