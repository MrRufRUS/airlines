package ru.tsyplakov.airlines.domain;

public abstract class BaseEntity {

    private final Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
