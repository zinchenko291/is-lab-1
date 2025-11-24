package me.zinch.is.islab1jee8.controllers;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import me.zinch.is.islab1jee8.models.dao.SortDirection;
import me.zinch.is.islab1jee8.controllers.fields.VehicleField;
import me.zinch.is.islab1jee8.models.dao.IVehicleDao;
import me.zinch.is.islab1jee8.models.dao.fields.WheelRange;
import me.zinch.is.islab1jee8.models.dto.VehiclePostDto;
import me.zinch.is.islab1jee8.models.dto.VehiclePutDto;
import me.zinch.is.islab1jee8.models.entities.FuelType;
import me.zinch.is.islab1jee8.models.entities.Vehicle;

@Path("/vehicle")
public class VehicleController {
    private final IVehicleDao vehicleDao;

    @Inject
    public VehicleController(final IVehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicle(
            @PathParam("id") Integer id
    ) {
        Vehicle vehicle = vehicleDao.findById(id);
        if (vehicle == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .ok(vehicle)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicles(
            @QueryParam("field") VehicleField field,
            @QueryParam("value") String value,
            @QueryParam("order_by") SortDirection sortDirection,
            @QueryParam("limit") Integer limit,
            @QueryParam("offset") Integer offset
            ) {
        return Response
                .ok(vehicleDao.findAll(field, value, limit, offset, sortDirection))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVehicle(
            VehiclePostDto vehiclePostDto
    ) {
        Vehicle vehicle = vehicleDao.create(vehiclePostDto);
        return Response
                .accepted(vehicle)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateVehicle(VehiclePutDto vehiclePutDto) {
        Vehicle vehicle = vehicleDao.updateById(vehiclePutDto.getId(), vehiclePutDto.getVehicle());
        return Response
                .accepted(vehicle)
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVehicle(@QueryParam("id") Integer id) {
        return Response
                .ok(vehicleDao.deleteById(id))
                .build();
    }

    @DELETE
    @Path("/by-fuel-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByFuelType(@QueryParam("fuelType") FuelType fuelType) {
        return Response.ok(vehicleDao.deleteByFuelType(fuelType))
                .build();
    }

    @GET
    @Path("/fuel-consumption-sum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFuelConsumptionSum() {
        return Response.ok(vehicleDao.getFuelConsumptionSum())
                .build();
    }

    @GET
    @Path("/by-fuel-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByFuelType(@QueryParam("fuelType") FuelType fuelType) {
        return Response.ok(vehicleDao.getByGteFuelType(fuelType))
                .build();
    }

    @GET
    @Path("/by-wheel-range")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByWheelRange(@QueryParam("min") Integer min, @QueryParam("max") Integer max) {
        WheelRange wheelRange = new WheelRange();
        wheelRange.setMin(min);
        wheelRange.setMax(max);
        return Response.ok(vehicleDao.getByWheelRange(wheelRange))
                .build();
    }

    @PUT
    @Path("/reset-distance-travelled")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetDistanceTravelled(@QueryParam("id") Integer id) {
        return Response.ok(vehicleDao.resetDistanceTravelledById(id))
                .build();
    }
}
