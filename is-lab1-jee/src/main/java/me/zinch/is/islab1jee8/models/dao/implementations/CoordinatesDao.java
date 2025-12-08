package me.zinch.is.islab1jee8.models.dao.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import me.zinch.is.islab1jee8.models.entities.Coordinates;
import me.zinch.is.islab1jee8.models.fields.CoordinatesField;


@ApplicationScoped
public class CoordinatesDao extends AbstractDao<Coordinates, CoordinatesField> {
    public CoordinatesDao() {
        super(Coordinates.class);
    }
}
