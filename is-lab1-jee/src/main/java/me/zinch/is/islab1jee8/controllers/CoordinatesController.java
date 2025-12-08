package me.zinch.is.islab1jee8.controllers;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesDto;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesWithoutIdDto;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.fields.SortDirection;
import me.zinch.is.islab1jee8.services.CoordinatesService;

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
            @QueryParam("limit") @Min(0) @DefaultValue("50") Integer limit,
            @QueryParam("offset") @Min(0) @DefaultValue("0") Integer offset
            ) {
        return Response
                .ok(service.findAll(field, value, orderBy, limit, offset))
            .build();
    }
}
