package ru.tsyplakov.airlines.dto.booking;

import jakarta.validation.constraints.*;
import ru.tsyplakov.airlines.domain.BookingStatus;
import ru.tsyplakov.airlines.domain.ServiceClass;

import java.math.BigDecimal;

public record BookingUpdateRequest(

        @NotNull
        @Positive
        Long userId,

        @NotNull
        @Positive
        Long flightId,

        @NotBlank
        @Size(max = 5)
        String seatNumber,

        @NotNull
        ServiceClass serviceClass,

        @NotNull
        @Positive
        @Digits(integer = 8, fraction = 2)
        BigDecimal price,

        @NotNull
        BookingStatus status

) {}