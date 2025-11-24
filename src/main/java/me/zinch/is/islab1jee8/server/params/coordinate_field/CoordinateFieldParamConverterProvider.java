package me.zinch.is.islab1jee8.server.params.coordinate_field;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import me.zinch.is.islab1jee8.controllers.fields.CoordinatesField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class CoordinateFieldParamConverterProvider implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> clazz, Type type, Annotation[] annotations) {
        if (clazz.equals(CoordinatesField.class)) {
            @SuppressWarnings("unchecked") // была выполнена проверка, что clazz это CoordinateField
            ParamConverter<T> pc = (ParamConverter<T>) new CoordinateFieldConverter();
            return pc;
        }
        return null;
    }
}
