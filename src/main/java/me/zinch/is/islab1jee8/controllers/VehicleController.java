package me.zinch.is.islab1jee8.controllers;

import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleDto;
import me.zinch.is.islab1jee8.models.entities.Vehicle;
import me.zinch.is.islab1jee8.models.fields.SortDirection;
import me.zinch.is.islab1jee8.models.fields.VehicleField;
import me.zinch.is.islab1jee8.services.VehicleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("vehicle")
public class VehicleController extends AbstractController<Vehicle, VehicleField, VehicleDto> {
    @Inject
    public VehicleController(VehicleService vehicleService) {
        super(vehicleService);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMany(
            @QueryParam("field") VehicleField field,
            @QueryParam("value") String value,
            @QueryParam("orderBy") SortDirection orderBy,
            @QueryParam("limit") @DefaultValue("50") Integer limit,
            @QueryParam("offset") @DefaultValue("0") Integer offset
    ) {
        return Response
                .ok(service.findAll(field, value, orderBy, limit, offset))
                .build();
    }

//    @GET
//    @Path("find-with-min-engine-power")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findWithMinEnginePower() {
//        return Response.ok(vehicleService.findMinEnginePower())
//                .build();
//    }
}
