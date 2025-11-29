package me.zinch.is.islab1jee8.models.dto.vehicle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.entities.FuelType;
import me.zinch.is.islab1jee8.models.entities.VehicleType;

public class VehiclePostDto {
    @NotBlank
    private String name;

    @NotNull
    private Coordinates coordinates;

    private VehicleType type;

    @Min(1)
    private int enginePower;

    @Min(1)
    private Long numberOfWheels;

    @Min(1)
    private Double capacity;

    @Min(1)
    private int distanceTravelled;

    @Min(1)
    private double fuelConsumption;

    @NotNull
    private FuelType fuelType;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotNull Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(@NotNull Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Min(1)
    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(@Min(1) int enginePower) {
        this.enginePower = enginePower;
    }

    public @Min(1) Long getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(@Min(1) Long numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public @Min(1) Double getCapacity() {
        return capacity;
    }

    public void setCapacity(@Min(1) Double capacity) {
        this.capacity = capacity;
    }

    @Min(1)
    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(@Min(1) int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    @Min(1)
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(@Min(1) double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public @NotNull FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(@NotNull FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
