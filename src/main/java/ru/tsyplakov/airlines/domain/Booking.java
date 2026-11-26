package ru.tsyplakov.airlines.domain;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking extends BaseEntity {

    private final Long userId;
    private final Long flightId;
    private String seatNumber;
    private ServiceClass serviceClass;
    private BigDecimal price;
    private BookingStatus bookingStatus;
    private Instant bookedAt;

    public Booking(
            Long id,
            Long userId,
            Long flightId,
            String seatNumber,
            ServiceClass serviceClass,
            BigDecimal price) {
        super(id);
        this.userId = userId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.serviceClass = serviceClass;
        this.price = price;
        this.bookingStatus = BookingStatus.PENDING;
        this.bookedAt = Instant.now();
    }
}
