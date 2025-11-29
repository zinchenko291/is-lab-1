package me.zinch.is.islab1jee8.services;

import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.fields.Filter;
import me.zinch.is.islab1jee8.models.fields.Page;
import me.zinch.is.islab1jee8.models.fields.SortDirection;
import me.zinch.is.islab1jee8.models.dao.implementations.CoordinatesDao;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesDto;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesMapper;
import me.zinch.is.islab1jee8.models.dto.coordinates.CoordinatesWithoutIdDto;
import me.zinch.is.islab1jee8.models.entities.Coordinates;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ManagedBean
@ApplicationScoped
public class CoordinatesService {
    private CoordinatesDao coordinatesDao;
    private CoordinatesMapper mapper;

    public CoordinatesService() {
    }

    @Inject
    public CoordinatesService(
            CoordinatesDao coordinatesDao,
            CoordinatesMapper mapper
    ) {
        this.coordinatesDao = coordinatesDao;
        this.mapper = mapper;
    }

    public Page<CoordinatesDto> findAll(CoordinatesField field, String value, SortDirection orderBy, Integer pageSize, Integer page) {
        Filter<CoordinatesField> filter = new Filter<>(field, value, orderBy);
        Long counter = coordinatesDao.count();
        List<Coordinates> coordinates = coordinatesDao.findAllPaged(page, pageSize, filter);
        return new Page<>(
                counter,
                coordinates.stream().map(this.mapper::entityToDto).collect(Collectors.toList())
        );
    }

    public CoordinatesDto findById(Integer id) {
        return coordinatesDao.findById(id)
                .map(this.mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Не существует координаты по id = %s", id)));
    }

    @Transactional
    public CoordinatesDto create(CoordinatesWithoutIdDto coordinatesWithoutIdDto) {
        Coordinates createdCoordinates = coordinatesDao.create(mapper.dtoToEntity(coordinatesWithoutIdDto));
        return mapper.entityToDto(createdCoordinates);
    }

    @Transactional
    public CoordinatesDto updateById(Integer id, CoordinatesWithoutIdDto coordinatesWithoutIdDto) {
        Coordinates coordinates = mapper.dtoToEntity(coordinatesWithoutIdDto);
        coordinates.setId(id);
        Coordinates createdCoordinates = coordinatesDao.update(coordinates);
        return mapper.entityToDto(createdCoordinates);
    }

    @Transactional
    public CoordinatesDto deleteById(Integer id) {
        Optional<Coordinates> coordinates = coordinatesDao.findById(id);
        return coordinates.map(value -> mapper.entityToDto(coordinatesDao.delete(value))).orElse(null);
    }
}
