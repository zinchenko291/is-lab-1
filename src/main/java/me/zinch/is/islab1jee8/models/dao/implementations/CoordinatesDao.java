package me.zinch.is.islab1jee8.models.dao.implementations;

import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class CoordinatesDao extends AbstractDao<Coordinates, CoordinatesField> {
    public CoordinatesDao() {
        super(Coordinates.class);
    }
}
