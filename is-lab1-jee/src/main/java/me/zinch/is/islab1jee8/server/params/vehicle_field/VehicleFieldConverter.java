package me.zinch.is.islab1jee8.server.params.vehicle_field;

import me.zinch.is.islab1jee8.exceptions.DeserializingException;
import me.zinch.is.islab1jee8.models.fields.VehicleField;

import jakarta.ws.rs.ext.ParamConverter;

public class VehicleFieldConverter implements ParamConverter<VehicleField> {
    @Override
    public VehicleField fromString(String s) {
        try {
            return new VehicleField(s);
        } catch (IllegalArgumentException e) {
            throw new DeserializingException(
                    String.format("Поля %s не существует в сущности Vehicle", s)
            );
        }
    }

    @Override
    public String toString(VehicleField vehicleField) {
        return vehicleField.getValue();
    }
}
