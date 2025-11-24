package me.zinch.is.islab1jee8.controllers;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import me.zinch.is.islab1jee8.controllers.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.dao.SortDirection;
import me.zinch.is.islab1jee8.models.dao.ICoordinatesDao;
import me.zinch.is.islab1jee8.models.entities.Coordinates;

@Path("/coordinates")
public class CoordinatesController {
    private final ICoordinatesDao coordinatesDao;

    @Inject
    public CoordinatesController(ICoordinatesDao coordinatesDao) {
        this.coordinatesDao = coordinatesDao;
    }

    @GET
    public Response getCoordinates(
            @QueryParam("field") CoordinatesField field,
            @QueryParam("value") String value,
            @QueryParam("order_by") SortDirection orderBy,
            @QueryParam("limit") Integer limit,
            @QueryParam("offset") Integer offset
    ) {
        return Response.ok(
                coordinatesDao.findAll(field, value, orderBy, limit, offset)
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCoordinates(Coordinates coordinates) {
        return Response.status(201)
                .entity(coordinatesDao.create(coordinates))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCoordinates(
            @QueryParam("x") Double x,
            @QueryParam("y") Double y,
            Coordinates coordinates
    ) {
        Coordinates newCoordinate = new Coordinates();
        newCoordinate.setX(x);
        newCoordinate.setY(y);
        return Response.ok()
                .entity(coordinatesDao.updateById(newCoordinate, coordinates))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCoordinates(
            @QueryParam("x") Double x,
            @QueryParam("y") Double y
    ) {
        Coordinates coordinate = new Coordinates();
        coordinate.setX(x);
        coordinate.setY(y);
        return Response.ok()
                .entity(coordinatesDao.deleteById(coordinate))
                .build();
    }
}
