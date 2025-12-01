package me.zinch.is.islab1jee8.controllers;

import me.zinch.is.islab1jee8.models.fields.EntityField;
import me.zinch.is.islab1jee8.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class AbstractController<E, F extends EntityField, D, I> {
    protected final AbstractService<E, F, D, I> service;

    protected AbstractController(AbstractService<E, F, D, I> service) {
        this.service = service;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(
            @PathParam("id") Integer id
    ) {
        return Response.ok(service.findById(id))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(I dto) {
        return Response.status(201)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @PathParam("id") Integer id,
            D dto
    ) {
        return Response.ok()
                .entity(service.updateById(id, dto))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("id") Integer id
    ) {
        return Response.ok()
                .entity(service.deleteById(id))
                .build();
    }
}
