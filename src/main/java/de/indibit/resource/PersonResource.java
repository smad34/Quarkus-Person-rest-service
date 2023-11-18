package de.indibit.resource;

import de.indibit.config.MessageSource;
import de.indibit.domain.Person;
import de.indibit.config.ErrorResponse;
import de.indibit.service.PersonService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.RequestScoped;
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

@Path(PersonResource.Person_API)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PersonResource {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(PersonResource.class));
    static final String Person_API = "/persons";

    @Inject
    PersonService personService;
    @Inject
    MessageSource messageSource;

    /**
     * Provides person list.
     *
     * @return all available persons.
     */
    @Operation(summary = "Get Persons from Database", description = "Retrieves a list of persons from the database.")
    @APIResponse(responseCode = "200", description = "Successful response", content = @Content(
            mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = Person.class)))
    @APIResponse(responseCode = "500", description = "Internal Server Error",
            content = @Content(mediaType = "application/json", schema = @Schema(type = SchemaType.OBJECT, implementation = ErrorResponse.class)))
    @GET
    public Multi<Response> getPersons() {
        return Multi.createFrom().item(() -> {
            try {
                List<Person> persons = personService.getPersons();
                return Response.ok(persons).build();
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse("500", "An internal server error occurred.");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }


    /**
     * Provides person based on its id.
     *
     * @param id to find person
     * @return matching person
     */
    @GET
    @Path("/{id}")
    public Uni<Response> getPersonById(@PathParam("id") Long id) {
      return   Uni.createFrom().item(() -> {
                    Person person = personService.findById(id);
                    if (person != null) {
                        return Response.ok(person).build();
                    }
                    return Response.status(Response.Status.NOT_FOUND)
                            .entity(messageSource.getMessage("person.notfound", id.toString()))
                            .build();
                }
        ).runSubscriptionOn(Infrastructure.getDefaultExecutor());
    }
    /**
     * Adds a new person to the repository.
     *
     * @param person The person object to be added.
     * @return The added person.
     */
    @POST
    @Transactional
    public Response addPerson(Person person) {
        if (personService.createOrUpdatePerson(person))
            return Response.created(URI.create("/persons/" + person.getId())).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * update a person in the repository.
     *
     * @param id The person object to be updated.
     * @return person with optional message.
     */
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Long id, Person updatedPerson) {
        Person person = personService.findById(id);
        if (person != null) {
            updatedPerson.setId(id);
            personService.createOrUpdatePerson(updatedPerson);
            return Response.ok(updatedPerson).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * delete a person in the repository.
     *
     * @param id The person object to be deleted.
     * @return status with optional message.
     */
    @Path("byId/{id}")
    @DELETE
    @Transactional
    public Response deletePerson(@PathParam("id") Long id) {
        if (personService.remove(id)) {
            return Response.ok(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * delete a person by firstName in the repository.
     *
     * @param firstName The key for finding person object.
     * @return status with optional message.
     */
    @Path("byFirstName/{firstName}")
    @DELETE
    @Transactional
    public Response deletePersonByFirstName(@PathParam("firstName") String firstName) {
        if (personService.deleteByName(firstName)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

