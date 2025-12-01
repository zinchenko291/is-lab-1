package me.zinch.is.islab1jee8.services;

import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.dao.implementations.CoordinatesDao;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesDto;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesMapper;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesWithoutIdDto;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ManagedBean
@ApplicationScoped
public class CoordinatesService extends AbstractService<Coordinates, CoordinatesField, CoordinatesDto, CoordinatesWithoutIdDto> {

    public CoordinatesService() {}

    @Inject
    public CoordinatesService(
            CoordinatesDao dao,
            CoordinatesMapper mapper
    ) {
        super(dao, mapper);
    }

    @Override
    @Transactional
    public CoordinatesDto updateById(Integer id, CoordinatesDto dto) {
        return dao.findById(id)
                .map(obj -> {
                    Coordinates coordinates = mapper.dtoToEntity(dto);
                    coordinates.setId(obj.getId());
                    return coordinates;
                })
                .map(dao::update)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Override
    protected String getResourceExceptionMessage(Integer id) {
        return String.format("Не существует координаты по id = %s", id);
    }
}
