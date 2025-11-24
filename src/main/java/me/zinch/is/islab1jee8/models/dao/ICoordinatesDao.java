package me.zinch.is.islab1jee8.models.dao;

import me.zinch.is.islab1jee8.controllers.fields.CoordinatesField;
import me.zinch.is.islab1jee8.models.entities.Coordinates;

import java.util.List;

public interface ICoordinatesDao {
    public List<Coordinates> findAll();
    public Page<Coordinates> findAllPaged(int page, int pageSize, Filter<CoordinatesField> filter, SortDirection sortDirection);
    public Coordinates create(Coordinates coordinates);
    public Coordinates update(Coordinates coordinates);
    public Coordinates delete(Coordinates coordinates);
}
