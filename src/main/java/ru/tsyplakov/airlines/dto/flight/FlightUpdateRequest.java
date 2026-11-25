package ru.tsyplakov.airlines.dto.flight;

import java.time.Instant;
import jakarta.validation.constraints.*;

import jakarta.validation.constraints.*;
import java.time.Instant;

public record FlightUpdateRequest(

        @Size(max = 10)
        @Pattern(regexp = "(?s).*\\S.*")
        String flightNumber,

        @Pattern(regexp = "[A-Z]{4}")
        String departureICAO,

        @Pattern(regexp = "[A-Z]{4}")
        String arrivalICAO,

        Instant departureTime,

        Instant arrivalTime,

        @Positive
        Integer capacity

) {}