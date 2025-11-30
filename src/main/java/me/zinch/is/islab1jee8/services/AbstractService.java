package me.zinch.is.islab1jee8.services;

import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IDao;
import me.zinch.is.islab1jee8.models.dto.IMapper;
import me.zinch.is.islab1jee8.models.fields.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<E, F extends EntityField, D> {
    protected final IDao<E, F> dao;
    protected final IMapper<E, D> mapper;

    protected AbstractService(IDao<E, F> dao, IMapper<E, D> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public Page<D> findAll(F field, String value, SortDirection orderBy, Integer pageSize, Integer page) {
        Filter<F> filter = new Filter<>(field, value, orderBy);
        Long counter = dao.countPaged(filter);
        List<E> coordinates = dao.findAllPaged(page, pageSize, filter);
        return new Page<>(
                counter,
                coordinates.stream().map(this.mapper::toDto).collect(Collectors.toList())
        );
    }

    public D findById(Integer id) {
        return dao.findById(id)
                .map(this.mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Transactional
    public D create(D dto) {
        E createdCoordinates = dao.create(mapper.toEntity(dto));
        return mapper.toDto(createdCoordinates);
    }

    public abstract D updateById(Integer id, D dto);

    @Transactional
    public D deleteById(Integer id) {
        Optional<E> coordinates = dao.findById(id);
        return coordinates
                .map(dao::delete)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    protected abstract String getResourceExceptionMessage(Integer id);
}
