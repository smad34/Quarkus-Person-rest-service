package de.indibit.entity;

import de.indibit.domain.Person;
import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

/**
 * <b>Title:</b> PersonEntity <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate mapping of {@link Person}.<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="person")
public class PersonEntity  {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
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
