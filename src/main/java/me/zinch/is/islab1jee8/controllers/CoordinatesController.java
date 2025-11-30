package me.zinch.is.islab1jee8.controllers;

import me.zinch.is.islab1jee8.models.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.fields.SortDirection;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesWithoutIdDto;
import me.zinch.is.islab1jee8.services.CoordinatesService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/coordinates")
public class CoordinatesController {
    private final CoordinatesService service;

    @Inject
    public CoordinatesController(CoordinatesService service) {
        this.service = service;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoordinates(
            @QueryParam("field") CoordinatesField field,
            @QueryParam("value") String value,
            @QueryParam("orderBy") SortDirection orderBy,
            @QueryParam("limit") @DefaultValue("50") Integer limit,
            @QueryParam("offset") @DefaultValue("0") Integer offset
    ) {
        return Response
                .ok(service.findAll(field, value, orderBy, limit, offset))
            .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoordinate(
            @PathParam("id") Integer id
    ) {
        return Response
                .ok(service.findById(id))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCoordinates(CoordinatesWithoutIdDto coordinates) {
        return Response
                .status(201)
                .entity(service.create(coordinates))
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCoordinates(
            @PathParam("id") Integer id,
            CoordinatesWithoutIdDto coordinates
    ) {
        return Response
                .ok(service.updateById(id, coordinates))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCoordinates(
            @PathParam("id") Integer id
    ) {
        return Response
                .ok(service.deleteById(id))
                .build();
    }
}
