package me.zinch.is.islab1jee8.controllers;

import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesDto;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.fields.SortDirection;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesWithoutIdDto;
import me.zinch.is.islab1jee8.services.CoordinatesService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("coordinates")
public class CoordinatesController extends AbstractController<Coordinates, CoordinatesField, CoordinatesDto, CoordinatesWithoutIdDto> {
    @Inject
    public CoordinatesController(CoordinatesService service) {
        super(service);
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
}
