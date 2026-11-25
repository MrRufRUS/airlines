package ru.tsyplakov.airlines.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseEntity {

    private final Long id;
}
