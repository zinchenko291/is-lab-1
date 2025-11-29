package me.zinch.is.islab1jee8.server.params.vehicle_field;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import me.zinch.is.islab1jee8.models.fields.VehicleField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class VehicleFieldParamConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> clazz, Type type, Annotation[] annotations) {
        if (clazz.equals(VehicleField.class)) {
            @SuppressWarnings("unchecked") // была выполнена проверка, что clazz это VehicleField
            ParamConverter<T> pc = (ParamConverter<T>) new VehicleFieldConverter();
            return pc;
        }
        return null;
    }
}
