package de.indibit.entity;

import io.quarkus.security.IdentityAttribute;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Entity
@Data
public class PersonEntity{
    @Id
    @Column
    @GeneratedValue
    @Schema(required = true,defaultValue = "0",description = "the id is mandatory")
    private Long id;
    @Column
    @Schema(required = true, description = "title of the book", example = "George Lucas")
    private String firstName;
    @Column
    private String lastName;
    @Column
    private int age;


}
