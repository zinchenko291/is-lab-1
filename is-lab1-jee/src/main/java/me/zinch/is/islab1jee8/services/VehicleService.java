package me.zinch.is.islab1jee8.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IVehicleDao;
import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleDto;
import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleMapper;
import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleWithoutIdDto;
import me.zinch.is.islab1jee8.models.entities.FuelType;
import me.zinch.is.islab1jee8.models.entities.Vehicle;
import me.zinch.is.islab1jee8.models.fields.Page;
import me.zinch.is.islab1jee8.models.fields.Range;
import me.zinch.is.islab1jee8.models.fields.VehicleField;
import me.zinch.is.islab1jee8.models.ws.WsAction;
import me.zinch.is.islab1jee8.models.ws.WsEntity;

import java.util.List;

@ApplicationScoped
public class VehicleService extends AbstractService<Vehicle, VehicleField, VehicleDto, VehicleWithoutIdDto> {
    private IVehicleDao vehicleDao;

    public VehicleService() {
        super();
    }

    @Inject
    public VehicleService(IVehicleDao vehicleDao, VehicleMapper vehicleMapper, WebSocketService webSocketService) {
        super(vehicleDao, vehicleMapper, webSocketService);
        this.vehicleDao = vehicleDao;
    }

    @Override
    @Transactional
    public VehicleDto updateById(Integer id, VehicleDto dto) {
        return vehicleDao.findById(id)
                .map(v -> {
                    Vehicle vehicle = mapper.dtoToEntity(dto);
                    vehicle.setId(v.getId());
                    return vehicle;
                })
                .map(vehicleDao::update)
                .map(mapper::entityToDto)
                .map(this::sendUpdateEvent)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Override
    protected String getResourceExceptionMessage(Integer id) {
        return String.format("Не существует транспортного средства по id = %s", id);
    }

    public VehicleDto findMinEnginePower() {
        return vehicleDao.findMinEnginePower()
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("ТС с минимальной мощностью не найдено"));
    }

    public Long countGtFuelType(FuelType fuelType) {
        return vehicleDao.countGtFuelType(fuelType);
    }
    public Page<VehicleDto> findByNameSubstring(Integer page, Integer pageSize, String name) {
        List<Vehicle> vehicles = vehicleDao.findByNameSubstring(page, pageSize, name);
        Long total = vehicleDao.countByNameSubstring(name);

        List<VehicleDto> dtos = vehicles.stream()
                .map(mapper::entityToDto)
                .toList();

        return new Page<>(total, dtos);
    }

    public Page<VehicleDto> findByEnginePowerRange(Integer page, Integer pageSize, Range<Integer> range) {
        List<Vehicle> vehicles = vehicleDao.findByEnginePowerRange(page, pageSize, range);
        Long total = vehicleDao.countByEnginePowerRange(range);

        List<VehicleDto> dtos = vehicles.stream()
                .map(mapper::entityToDto)
                .toList();

        return new Page<>(total, dtos);
    }

    @Transactional
    public VehicleDto resetDistanceTravelledById(Integer id) {
        return vehicleDao.resetDistanceTravelledById(id)
            .map(mapper::entityToDto)
            .map(this::sendUpdateEvent)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            String.format("Не существует транспортного средства по id = %s", id)
                    )
            );
    }

    private VehicleDto sendUpdateEvent(VehicleDto vehicleDto) {
        webSocketService.sendEvent(WsEntity.VEHICLE, WsAction.UPDATE, vehicleDto.getId(), vehicleDto);
        return vehicleDto;
    }

    @Override
    protected WsEntity getWsEntity() {
        return WsEntity.VEHICLE;
    }

    @Override
    protected Integer getEntityId(Vehicle entity) {
        return entity.getId();
    }
}

