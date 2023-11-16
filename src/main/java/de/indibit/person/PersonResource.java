package de.indibit.person;

import groovy.xml.StreamingDOMBuilder;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(PersonResource.class));

    @Inject
    PersonService personService;


    @GET
    public Response getPersons() {
        List<PersonEntity> persons = personService.getPersons();
        LOGGER.warning("Error searching LDAP.");
        return Response.ok(persons).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        PersonEntity person = personService.findById(id);
        return Response.ok(person).build();
    }

    @POST
    @Transactional
    public Response addPerson(PersonEntity person) {
        if (personService.createOrUpdatePerson(person))
            return Response.created(URI.create("/persons/" + person.getId())).build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePerson(@PathParam("id") Long id, Person updatedPerson) {
        PersonEntity person = personService.findById(id);

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
    public Response deletePerson(@PathParam("id") Long id){
        if(personService.remove(id)){
            return Response.ok(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

