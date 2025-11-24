package me.zinch.is.islab1jee8.server.params.vehicle_field;

import javax.ws.rs.ext.ParamConverter;
import me.zinch.is.islab1jee8.controllers.fields.VehicleField;
import me.zinch.is.islab1jee8.exceptions.DeserializingException;

public class VehicleFieldConverter implements ParamConverter<VehicleField> {
    @Override
    public VehicleField fromString(String s) {
        try {
            return VehicleField.valueOf(s);
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
