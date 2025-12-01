package me.zinch.is.islab1jee8.services;

import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IDao;
import me.zinch.is.islab1jee8.models.dto.IMapper;
import me.zinch.is.islab1jee8.models.fields.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Abstract CRUD Service
 * @param <E> Entity
 * @param <F> EntityFields
 * @param <D> DTO
 * @param <I> DTO without ID field
 */
public abstract class AbstractService<E, F extends EntityField, D, I> {
    protected IDao<E, F> dao;
    protected IMapper<E, D, I> mapper;

    protected AbstractService() {}

    protected AbstractService(IDao<E, F> dao, IMapper<E, D, I> mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public Page<D> findAll(F field, String value, SortDirection orderBy, Integer pageSize, Integer page) {
        Filter<F> filter = new Filter<>(field, value, orderBy);
        Long counter = dao.countPaged(filter);
        List<E> coordinates = dao.findAllPaged(page, pageSize, filter);
        return new Page<>(
                counter,
                coordinates.stream().map(this.mapper::entityToDto).collect(Collectors.toList())
        );
    }

    public D findById(Integer id) {
        return dao.findById(id)
                .map(this.mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Transactional
    public D create(I dto) {
        E createdCoordinates = dao.create(mapper.idLessDtoToEntity(dto));
        return mapper.entityToDto(createdCoordinates);
    }

    public abstract D updateById(Integer id, D dto);

    @Transactional
    public D deleteById(Integer id) {
        Optional<E> coordinates = dao.findById(id);
        return coordinates
                .map(dao::delete)
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    protected abstract String getResourceExceptionMessage(Integer id);
}
