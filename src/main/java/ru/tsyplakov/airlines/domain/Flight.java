package ru.tsyplakov.airlines.domain;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Flight extends BaseEntity {
    private String flightNumber;
    private String departureICAO;
    private String arrivalICAO;
    private Instant departureTime; // время по UTC
    private Instant arrivalTime; // время по UTC
    private int capacity;

    public Flight(
            Long id,
            String flightNumber,
            String departureICAO,
            String arrivalICAO,
            Instant departureTime,
            Instant arrivalTime,
            int capacity) {
        super(id);
        this.flightNumber = flightNumber;
        this.departureICAO = departureICAO;
        this.arrivalICAO = arrivalICAO;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.capacity = capacity;
    }
}
