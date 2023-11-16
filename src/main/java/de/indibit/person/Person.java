package de.indibit.person;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "firstName", "lastName", "age"})
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
}
