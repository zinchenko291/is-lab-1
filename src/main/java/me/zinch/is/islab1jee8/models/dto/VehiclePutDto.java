package me.zinch.is.islab1jee8.models.dto;

import javax.validation.constraints.NotNull;

public class VehiclePutDto {
    @NotNull
    private Integer id;

    @NotNull
    private VehiclePostDto vehicle;

    public @NotNull Integer getId() {
        return id;
    }

    public @NotNull VehiclePostDto getVehicle() {
        return vehicle;
    }
}
