package ru.tsyplakov.airlines.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Booking {

    private final UUID id;
    private final UUID userId;
    private final UUID flightId;
    private String seatNumber;
    private ServiceClass serviceClass;
    private BigDecimal price;
    private String status;
    private Instant bookedAt;

    public Booking(UUID userId, UUID flightId, String seatNumber, ServiceClass serviceClass, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.serviceClass = serviceClass;
        this.price = price;
        this.status = BookingStatus.PENDING.name();
        this.bookedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public ServiceClass getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(ServiceClass serviceClass) {
        this.serviceClass = serviceClass;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Instant bookedAt) {
        this.bookedAt = bookedAt;
    }
}
