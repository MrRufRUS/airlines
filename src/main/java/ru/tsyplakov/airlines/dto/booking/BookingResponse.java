package ru.tsyplakov.airlines.dto.booking;

import ru.tsyplakov.airlines.domain.BookingStatus;
import ru.tsyplakov.airlines.domain.ServiceClass;

import java.math.BigDecimal;
import java.time.Instant;

public record BookingResponse(
        Long id,
        Long userId,
        Long flightId,
        String seatNumber,
        ServiceClass serviceClass,
        BigDecimal price,
        BookingStatus status,
        Instant bookedAt
) {}