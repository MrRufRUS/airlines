package ru.tsyplakov.airlines.dto.flight;

import java.time.Instant;

public record FlightResponse(
        Long id,
        String flightNumber,
        String departureIcao,
        String arrivalIcao,
        Instant departureTime,
        Instant arrivalTime,
        Integer capacity
) {
}
