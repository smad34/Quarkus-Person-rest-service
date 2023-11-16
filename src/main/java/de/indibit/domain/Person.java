package de.indibit.domain;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class Person implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

}
