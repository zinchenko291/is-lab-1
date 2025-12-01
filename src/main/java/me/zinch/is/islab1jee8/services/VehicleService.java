package me.zinch.is.islab1jee8.services;

import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IVehicleDao;
import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleDto;
import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleMapper;
import me.zinch.is.islab1jee8.models.dto.vehicle.VehicleWithoutIdDto;
import me.zinch.is.islab1jee8.models.entities.Vehicle;
import me.zinch.is.islab1jee8.models.fields.VehicleField;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ManagedBean
@ApplicationScoped
public class VehicleService extends AbstractService<Vehicle, VehicleField, VehicleDto, VehicleWithoutIdDto> {
    private IVehicleDao vehicleDao;

    public VehicleService() {super();}

    @Inject
    public VehicleService(IVehicleDao vehicleDao, VehicleMapper vehicleMapper) {
        super(vehicleDao, vehicleMapper);
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
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    //    public VehicleDto findMinEnginePower() {
//        return vehicleDao.findMinEnginePower()
//                .map(vehicleMapper::toDto)
//                .orElseThrow(() -> new ResourceNotFoundException("Нет транспортных средств"));
//    }


    @Override
    protected String getResourceExceptionMessage(Integer id) {
        return String.format("Не существует транспортного средства по id = %s", id);
    }
}
