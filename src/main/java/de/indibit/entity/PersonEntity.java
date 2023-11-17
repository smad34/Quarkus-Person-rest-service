package de.indibit.entity;

import de.indibit.domain.Person;
import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(required = true,defaultValue = "0",description = "the id is mandatory")
    public Long id;
    @Column
    @Schema(required = true, description = "title of the book", example = "George Lucas")
    public String firstName;
    @Column
    public String lastName;
    @Column
    public int age;

    public Person toDomain(){
        return new Person(this.id,this.firstName,this.lastName,this.age);
    }
}
