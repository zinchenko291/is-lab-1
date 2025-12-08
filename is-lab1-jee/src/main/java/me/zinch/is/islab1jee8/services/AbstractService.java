package me.zinch.is.islab1jee8.services;

import jakarta.transaction.Transactional;
import me.zinch.is.islab1jee8.exceptions.ResourceNotFoundException;
import me.zinch.is.islab1jee8.models.dao.interfaces.IDao;
import me.zinch.is.islab1jee8.models.dto.IMapper;
import me.zinch.is.islab1jee8.models.fields.EntityField;
import me.zinch.is.islab1jee8.models.fields.Filter;
import me.zinch.is.islab1jee8.models.fields.Page;
import me.zinch.is.islab1jee8.models.fields.SortDirection;
import me.zinch.is.islab1jee8.models.ws.WsAction;
import me.zinch.is.islab1jee8.models.ws.WsEntity;

import java.util.List;
import java.util.Optional;

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
    protected WebSocketService webSocketService;

    protected AbstractService() {}

    protected AbstractService(IDao<E, F> dao, IMapper<E, D, I> mapper, WebSocketService webSocketService) {
        this.dao = dao;
        this.mapper = mapper;
        this.webSocketService = webSocketService;
    }

    public Page<D> findAll(F field, String value, SortDirection orderBy, Integer pageSize, Integer page) {
        Filter<F> filter = new Filter<>(field, value, orderBy);
        Long counter = dao.countPaged(filter);
        List<E> entity = dao.findAllPaged(page, pageSize, filter);
        return new Page<>(
                counter,
                entity.stream().map(this.mapper::entityToDto).toList()
        );
    }

    public D findById(Integer id) {
        return dao.findById(id)
                .map(this.mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    @Transactional
    public D create(I dto) {
        E entity = dao.create(mapper.idLessDtoToEntity(dto));
        webSocketService.sendEvent(getWsEntity(), WsAction.CREATE, getEntityId(entity), entity);
        return mapper.entityToDto(entity);
    }

    public abstract D updateById(Integer id, D dto);

    @Transactional
    public D deleteById(Integer id) {
        Optional<E> entity = dao.findById(id);
        return entity
                .map(dao::delete)
                .map(dto -> {
                    webSocketService.sendEvent(getWsEntity(), WsAction.DELETE, getEntityId(dto), dto);
                    return dto;
                })
                .map(mapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException(getResourceExceptionMessage(id)));
    }

    protected abstract String getResourceExceptionMessage(Integer id);

    protected abstract WsEntity getWsEntity();
    protected abstract Integer getEntityId(E entity);
}
