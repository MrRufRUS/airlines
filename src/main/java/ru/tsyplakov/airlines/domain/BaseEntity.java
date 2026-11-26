package ru.tsyplakov.airlines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class BaseEntity {

    private final Long id;

    public BaseEntity(Long id) {
        this.id = id;
    }
}
