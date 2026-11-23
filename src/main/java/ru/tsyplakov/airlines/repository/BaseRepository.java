package ru.tsyplakov.airlines.repository;

import ru.tsyplakov.airlines.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepository<T extends BaseEntity> {

    private final Map<Long, T> repository =
            new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public Long getNextId() {
        return idGenerator.getAndIncrement();
    }

    public void create(T entity) {
        repository.put(entity.getId(), entity);
    }

    public T getById(Long id) {
        return repository.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(repository.values());
    }

    public void update(T entity) {
        repository.put(entity.getId(), entity);
    }

    public void delete(Long id) {
        repository.remove(id);
    }
}