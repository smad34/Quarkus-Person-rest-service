package de.indibit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

/**
 * <b>Title:</b> PersonEntity <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate mapping of {@link PersonEntity}.<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Entity
@Table(name = "person")
@Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(required = true, defaultValue = "0", description = "The id is mandatory")
    public Long id;

    @Column
    @Schema(required = true, description = "First name of the person", example = "George")
    public String firstName;

    @Column
    public String lastName;

    @Column
    public int age;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CarEntity> cars;

    // Constructors, getters, setters...

    // Ensure that getter methods are named according to JavaBean conventions
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public List<CarEntity> getCars() {
        return cars;
    }
}