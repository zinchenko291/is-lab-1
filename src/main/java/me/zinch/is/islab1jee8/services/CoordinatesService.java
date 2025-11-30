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
        Long counter = coordinatesDao.countPaged(filter);
        List<Coordinates> coordinates = coordinatesDao.findAllPaged(page, pageSize, filter);
        return new Page<>(
                counter,
                coordinates.stream().map(this.mapper::toDto).collect(Collectors.toList())
        );
    }

    public CoordinatesDto findById(Integer id) {
        return coordinatesDao.findById(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Transactional
    public CoordinatesDto create(CoordinatesWithoutIdDto coordinatesWithoutIdDto) {
        Coordinates createdCoordinates = coordinatesDao.create(mapper.toEntity(coordinatesWithoutIdDto));
        return mapper.toDto(createdCoordinates);
    }

    @Transactional
    public CoordinatesDto updateById(Integer id, CoordinatesWithoutIdDto coordinatesWithoutIdDto) {
        return coordinatesDao.findById(id)
                .map(v -> {
                    v.setX(coordinatesWithoutIdDto.getX());
                    v.setY(coordinatesWithoutIdDto.getY());
                    return v;
                })
                .map(v -> coordinatesDao.update(v))
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Transactional
    public CoordinatesDto deleteById(Integer id) {
        Optional<Coordinates> coordinates = coordinatesDao.findById(id);
        return coordinates
                .map(value -> coordinatesDao.delete(value))
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    private String getResourceExceptionMessage(Integer id) {
        return String.format("Не существует координаты по id = %s", id);
    }
}
