package me.zinch.is.islab1jee8.models.dto;

public interface IMapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
