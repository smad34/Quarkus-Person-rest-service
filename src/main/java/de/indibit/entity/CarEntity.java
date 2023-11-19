package de.indibit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>Title:</b> PersonEntity <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate mapping of {@link CarEntity}.<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Entity
@Table(name = "car")
@Setter
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(required = true, defaultValue = "0", description = "The id is mandatory")
    private Long id;

    @Column
    private String model;

    @ManyToOne
    @JoinColumn(name = "person_id")
    public PersonEntity person;

    // Constructors, getters, setters...

    // Ensure that getter methods are named according to JavaBean conventions
    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public PersonEntity getPerson() {
        return person;
    }
}
