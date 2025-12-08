package me.zinch.is.islab1jee8.models.dto.coordinates;

import me.zinch.is.islab1jee8.models.dto.IMapper;
import me.zinch.is.islab1jee8.models.entities.Coordinates;


import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CoordinatesMapper implements IMapper<Coordinates, CoordinatesDto, CoordinatesWithoutIdDto> {
    @Override
    public Coordinates dtoToEntity(CoordinatesDto coordinatesDto) {
        if (coordinatesDto == null) return null;
        Coordinates coordinates = new Coordinates();
        coordinates.setId(coordinatesDto.getId());
        coordinates.setX(coordinatesDto.getX());
        coordinates.setY(coordinatesDto.getY());
        return coordinates;
    }

    @Override
    public Coordinates idLessDtoToEntity(CoordinatesWithoutIdDto coordinatesDto) {
        if (coordinatesDto == null) return null;
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesDto.getX());
        coordinates.setY(coordinatesDto.getY());
        return coordinates;
    }

    @Override
    public CoordinatesDto entityToDto(Coordinates coordinates) {
        if (coordinates == null) return null;
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setId(coordinates.getId());
        coordinatesDto.setX(coordinates.getX());
        coordinatesDto.setY(coordinates.getY());
        return coordinatesDto;
    }
}
