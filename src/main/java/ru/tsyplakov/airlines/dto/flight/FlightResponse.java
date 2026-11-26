package ru.tsyplakov.airlines.dto.flight;

import java.time.Instant;

public record FlightResponse(
        Long id,
        String flightNumber,
        String departureICAO,
        String arrivalICAO,
        Instant departureTime,
        Instant arrivalTime,
        Integer capacity) {}
