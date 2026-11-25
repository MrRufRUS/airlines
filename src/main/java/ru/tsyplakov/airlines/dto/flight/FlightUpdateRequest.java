package ru.tsyplakov.airlines.dto.flight;

import java.time.Instant;
import jakarta.validation.constraints.*;

public record FlightUpdateRequest(

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
        Instant departureTime,

        @NotNull
        Instant arrivalTime,

        @NotNull
        @Positive
        Integer capacity

) {}