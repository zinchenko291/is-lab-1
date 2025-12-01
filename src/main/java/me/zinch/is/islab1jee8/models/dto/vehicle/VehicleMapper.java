package me.zinch.is.islab1jee8.models.dto.vehicle;

import me.zinch.is.islab1jee8.models.dto.IMapper;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesMapper;
import me.zinch.is.islab1jee8.models.entities.Vehicle;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ManagedBean
@ApplicationScoped
public class VehicleMapper implements IMapper<Vehicle, VehicleDto, VehicleWithoutIdDto> {
    private CoordinatesMapper coordinatesMapper;

    public VehicleMapper() {}

    @Inject
    public VehicleMapper(CoordinatesMapper coordinatesMapper) {
        this.coordinatesMapper = coordinatesMapper;
    }

    @Override
    public VehicleDto entityToDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setName(vehicle.getName());
        vehicleDto.setCoordinates(coordinatesMapper.entityToDto(vehicle.getCoordinates()));
        vehicleDto.setCreationDate(vehicle.getCreationDate());
        vehicleDto.setType(vehicle.getType());
        vehicleDto.setEnginePower(vehicle.getEnginePower());
        vehicleDto.setNumberOfWheels(vehicle.getNumberOfWheels());
        vehicleDto.setCapacity(vehicle.getCapacity());
        vehicleDto.setDistanceTravelled(vehicle.getDistanceTravelled());
        vehicleDto.setFuelConsumption(vehicle.getFuelConsumption());
        vehicleDto.setFuelType(vehicle.getFuelType());
        return vehicleDto;
    }

    @Override
    public Vehicle idLessDtoToEntity(VehicleWithoutIdDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleDto.getName());
        vehicle.setCoordinates(coordinatesMapper.dtoToEntity(vehicleDto.getCoordinates()));
        vehicle.setType(vehicleDto.getType());
        vehicle.setEnginePower(vehicleDto.getEnginePower());
        vehicle.setNumberOfWheels(vehicleDto.getNumberOfWheels());
        vehicle.setCapacity(vehicleDto.getCapacity());
        vehicle.setDistanceTravelled(vehicleDto.getDistanceTravelled());
        vehicle.setFuelConsumption(vehicleDto.getFuelConsumption());
        vehicle.setFuelType(vehicleDto.getFuelType());
        return vehicle;
    }

    @Override
    public Vehicle dtoToEntity(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setName(vehicleDto.getName());
        vehicle.setCoordinates(coordinatesMapper.dtoToEntity(vehicleDto.getCoordinates()));
        vehicle.setCreationDate(vehicleDto.getCreationDate());
        vehicle.setType(vehicleDto.getType());
        vehicle.setEnginePower(vehicleDto.getEnginePower());
        vehicle.setNumberOfWheels(vehicleDto.getNumberOfWheels());
        vehicle.setCapacity(vehicleDto.getCapacity());
        vehicle.setDistanceTravelled(vehicleDto.getDistanceTravelled());
        vehicle.setFuelConsumption(vehicleDto.getFuelConsumption());
        vehicle.setFuelType(vehicleDto.getFuelType());
        return vehicle;
    }
}
