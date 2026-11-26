package ru.tsyplakov.airlines.domain;

import lombok.Getter;

@Getter
public abstract class BaseEntity {

    private final Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }
}
