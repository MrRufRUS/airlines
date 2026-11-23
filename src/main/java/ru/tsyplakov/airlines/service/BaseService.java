package ru.tsyplakov.airlines.service;

import ru.tsyplakov.airlines.domain.BaseEntity;
import ru.tsyplakov.airlines.repository.BaseRepository;

import java.util.List;

public abstract class BaseService<T extends BaseEntity> {

    protected final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public T getById(Long id) {
        return repository.getById(id);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
