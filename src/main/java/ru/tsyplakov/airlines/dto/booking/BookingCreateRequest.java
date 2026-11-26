package ru.tsyplakov.airlines.dto.booking;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import ru.tsyplakov.airlines.domain.ServiceClass;

public record BookingCreateRequest(
        @NotNull @Positive Long userId,
        @NotNull @Positive Long flightId,
        @NotBlank @Size(max = 5) String seatNumber,
        @NotNull ServiceClass serviceClass,
        @NotNull @Positive @Digits(integer = 8, fraction = 2) BigDecimal price) {}
