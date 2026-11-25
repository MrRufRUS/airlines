package ru.tsyplakov.airlines.mapper;

public interface ToResponseMapper<T, R> {
    R toResponse(T entity);
}
