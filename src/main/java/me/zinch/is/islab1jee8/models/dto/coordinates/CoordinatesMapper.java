package me.zinch.is.islab1jee8.models.dto.coordinates;

import me.zinch.is.islab1jee8.models.dto.IMapper;
import me.zinch.is.islab1jee8.models.entities.Coordinates;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class CoordinatesMapper implements IMapper<Coordinates, CoordinatesDto> {
    @Override
    public Coordinates toEntity(CoordinatesDto coordinatesDto) {
        Coordinates coordinates = new Coordinates();
        coordinates.setId(coordinatesDto.getId());
        coordinates.setX(coordinatesDto.getX());
        coordinates.setY(coordinatesDto.getY());
        return coordinates;
    }

    public Coordinates toEntity(CoordinatesWithoutIdDto coordinatesDto) {
        Coordinates coordinates = new Coordinates();
        coordinates.setX(coordinatesDto.getX());
        coordinates.setY(coordinatesDto.getY());
        return coordinates;
    }

    @Override
    public CoordinatesDto toDto(Coordinates coordinates) {
        CoordinatesDto coordinatesDto = new CoordinatesDto();
        coordinatesDto.setId(coordinates.getId());
        coordinatesDto.setX(coordinates.getX());
        coordinatesDto.setY(coordinates.getY());
        return coordinatesDto;
    }
}
