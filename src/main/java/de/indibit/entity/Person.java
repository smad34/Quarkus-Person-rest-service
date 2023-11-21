package de.indibit.entity;

import groovy.lang.Lazy;
import io.quarkus.hibernate.orm.runtime.boot.xml.QNameSubstitution;
import jakarta.persistence.*;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <b>Title:</b> Person <br>
 * <b>Copyright:</b> Copyright (c) 2023 <br>
 * <b>Company:</b> Indibit GmbH <br>
 * <b>Description:</b> Hibernate mapping of {@link Person}.<br>
 *
 * @author Mohammad
 * @version 1.0
 * @since 17.11.2023
 */
@Entity
@Table(name = "person")
@Setter
@Getter
@NoArgsConstructor
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(required = true, defaultValue = "0", description = "The id is mandatory")
    public Long id;

    @Column
    @Schema(required = true,
            description = "First name of the person",
            example = "George")
    public String firstName;

    @Column
    public String lastName;

    @Column
    public int age;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Car> cars = new HashSet<>();
}