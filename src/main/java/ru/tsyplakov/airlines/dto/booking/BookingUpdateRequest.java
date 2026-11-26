package ru.tsyplakov.airlines.dto.booking;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import ru.tsyplakov.airlines.domain.BookingStatus;
import ru.tsyplakov.airlines.domain.ServiceClass;

public record BookingUpdateRequest(
        @Size(max = 5) @Pattern(regexp = "(?s).*\\S.*") String seatNumber,
        ServiceClass serviceClass,
        @Positive @Digits(integer = 8, fraction = 2) BigDecimal price,
        BookingStatus status) {}
