package ru.tsyplakov.airlines.service;

import java.util.List;
import ru.tsyplakov.airlines.domain.BaseEntity;
import ru.tsyplakov.airlines.exception.NotFoundException;
import ru.tsyplakov.airlines.mapper.ToResponseMapper;
import ru.tsyplakov.airlines.repository.BaseRepository;

public abstract class BaseService<T extends BaseEntity, R, M extends ToResponseMapper<T, R>> {

    protected final BaseRepository<T> repository;
    protected final M mapper;

    public BaseService(BaseRepository<T> repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public R getById(Long id) {
        T entity = repository.getById(id);
        if (entity == null) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        return mapper.toResponse(entity);
    }

    public List<R> getAll() {
        return repository.getAll().stream().map(mapper::toResponse).toList();
    }

    public void delete(Long id) {
        T entity = repository.getById(id);
        if (entity == null) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        repository.delete(id);
    }
}
