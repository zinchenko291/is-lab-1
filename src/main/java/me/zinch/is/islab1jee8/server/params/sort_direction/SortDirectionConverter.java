package me.zinch.is.islab1jee8.server.params.sort_direction;

import javax.ws.rs.ext.ParamConverter;
import me.zinch.is.islab1jee8.models.dao.SortDirection;
import me.zinch.is.islab1jee8.exceptions.DeserializingException;

public class SortDirectionConverter implements ParamConverter<SortDirection> {
    @Override
    public SortDirection fromString(String str) {
        if (str == null) return null;
        if (str.equalsIgnoreCase("ASC")) return SortDirection.ASC;
        if (str.equalsIgnoreCase("DESC")) return SortDirection.DESC;
        throw new DeserializingException(
                String.format("Не удалось распознать значение %s. Параметр сортировки принимает значения только ASC или DESC", str)
        );
    }

    @Override
    public String toString(SortDirection sortDirection) {
        return sortDirection.getValue();
    }
}
