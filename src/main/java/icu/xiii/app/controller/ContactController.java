package icu.xiii.app.controller;

import icu.xiii.app.dto.contact.ContactDtoDeleteResponse;
import icu.xiii.app.dto.contact.ContactDtoRequest;
import icu.xiii.app.entity.Contact;
import icu.xiii.app.service.contact.ContactService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
public class ContactController {

    @Inject
    private ContactService contactService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createContact(final ContactDtoRequest request) {
        Contact contact = contactService.create(request);
        return Response
                .ok()
                .entity(contact)
                .build();
    }

    @GET
    public Response getAllContacts() {
        List<Contact> contacts = contactService.getAll();
        return Response
                .ok()
                .entity(contacts)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") final Long id) {
        Contact contact = contactService.getById(id);
        return Response
                .ok()
                .entity(contact)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") final Long id, final ContactDtoRequest request) {
        Contact contact = contactService.update(id, request);
        return Response
                .ok()
                .entity(contact)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteContactById(@PathParam("id") final Long id) {
        boolean isContactDeleted = contactService.deleteById(id);
        return Response
                .ok()
                .entity(ContactDtoDeleteResponse.of(id, isContactDeleted))
                .build();
    }
}
