package de.indibit.controller;

import de.indibit.domain.Person;
import de.indibit.entity.PersonEntity;
import de.indibit.service.PersonService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PersonController {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(PersonController.class));

    @Inject
    PersonService personService;

    @GET
    @Operation(summary = "Search LDAP entries.", description = "Returns LDAP entries and attributes.")
    @APIResponse(
            responseCode = "200",
            description = "current LDAP entry for ",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY)))
    public Response getPersons() {
        PanacheQuery<Person> persons = personService.getPersons();
        LOGGER.warning("Error searching LDAP.");
        return null;
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        Person person = personService.findById(id);
        return Response.ok(person).build();
    }

    @POST
    @Transactional
    public Response addPerson(Person person) {
        if (personService.createOrUpdatePerson(person))
            return Response.created(URI.create("/persons/" + person.getId())).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Long id, Person updatedPerson) {
        Person person = personService.findById(id);

        if (person != null) {
            person.setFirstName(updatedPerson.getFirstName());
            person.setLastName(updatedPerson.getLastName());
            person.setAge(updatedPerson.getAge());
            personService.createOrUpdatePerson(person);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("{id}")
    @DELETE
    @Transactional
    public Response deletePerson(@PathParam("id") Long id) {
        if (personService.remove(id)) {
            return Response.ok(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

