package ru.tsyplakov.airlines.dto.flight;

import jakarta.validation.constraints.*;

import java.time.Instant;

public record FlightCreateRequest(
        @NotBlank
        @Size(max = 10)
        String flightNumber,

        @NotBlank
        @Pattern(regexp = "[A-Z]{4}")
        String departureIcao,

        @NotBlank
        @Pattern(regexp = "[A-Z]{4}")
        String arrivalIcao,

        @NotNull
        @Future
        Instant departureTime,

        @NotNull
        @Future
        Instant arrivalTime,

        @NotNull
        @Positive
        Integer capacity
) {
}
