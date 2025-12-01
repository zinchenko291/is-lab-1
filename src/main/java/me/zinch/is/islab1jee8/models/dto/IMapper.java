package me.zinch.is.islab1jee8.models.dto;

public interface IMapper<E, D, I> {
    E dtoToEntity(D dto);
    E idLessDtoToEntity(I idLessDto);
    D entityToDto(E entity);
}
