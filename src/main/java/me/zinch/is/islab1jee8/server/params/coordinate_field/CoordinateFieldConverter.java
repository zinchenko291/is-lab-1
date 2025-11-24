package me.zinch.is.islab1jee8.server.params.coordinate_field;

import javax.ws.rs.ext.ParamConverter;
import me.zinch.is.islab1jee8.controllers.fields.CoordinatesField;
import me.zinch.is.islab1jee8.exceptions.DeserializingException;

public class CoordinateFieldConverter implements ParamConverter<CoordinatesField> {
    @Override
    public CoordinatesField fromString(String s) {
        try {
            return CoordinatesField.valueOf(s);
        } catch (IllegalArgumentException e) {
            throw new DeserializingException(
                    String.format("Поле %s отсутсвует в сущносте Coordinate", s)
            );
        }
    }

    @Override
    public String toString(CoordinatesField coordinatesField) {
        return coordinatesField.toString();
    }
}
