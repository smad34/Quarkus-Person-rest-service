package de.indibit.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

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
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(required = true, defaultValue = "0", description = "The id is mandatory")
    public Long id;

    @Column
    public String model;

    @ManyToOne
    @JsonbTransient
    @JoinColumn(name = "person_id")
    public Person person;

}
