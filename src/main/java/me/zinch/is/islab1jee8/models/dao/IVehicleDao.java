package me.zinch.is.islab1jee8.models.dao;

import me.zinch.is.islab1jee8.controllers.fields.VehicleField;
import me.zinch.is.islab1jee8.models.dao.fields.WheelRange;
import me.zinch.is.islab1jee8.models.dto.VehiclePostDto;
import me.zinch.is.islab1jee8.models.entities.FuelType;
import me.zinch.is.islab1jee8.models.entities.Vehicle;

import java.util.List;

public interface IVehicleDao {
    public List<Vehicle> findAll(VehicleField field, String value, Integer limit, Integer offset, SortDirection sortDirection);
    public Vehicle findById(int id);
    public Vehicle create(VehiclePostDto vehicle);
    public Vehicle updateById(int id, Vehicle vehicle);
    public Vehicle deleteById(int id);

    public Vehicle deleteByFuelType(FuelType fuelType);
    public Double getFuelConsumptionSum();
    public List<Vehicle> getByGteFuelType(FuelType fuelType);
    public List<Vehicle> getByWheelRange(WheelRange wheelRange);
    public Vehicle resetDistanceTravelledById(int id);
}
