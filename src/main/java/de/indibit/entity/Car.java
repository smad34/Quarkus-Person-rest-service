package de.indibit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * <b>Title:</b> Person <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate mapping of {@link Car}.<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Entity
@Table(name = "car")
@Setter
@Getter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(required = true, defaultValue = "0", description = "The id is mandatory")
    private Long id;

    @Column
    private String model;

    @ManyToOne
    @JoinColumn(name = "person_id")
    public Person person;

}
