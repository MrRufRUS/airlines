package ru.tsyplakov.airlines.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class Booking extends BaseEntity{

    private final Long userId;
    private final Long flightId;
    private String seatNumber;
    private ServiceClass serviceClass;
    private BigDecimal price;
    private BookingStatus bookingStatus;
    private Instant bookedAt;

    public Booking(Long id, Long userId, Long flightId, String seatNumber, ServiceClass serviceClass, BigDecimal price) {
        super(id);
        this.userId = userId;
        this.flightId = flightId;
        this.seatNumber = seatNumber;
        this.serviceClass = serviceClass;
        this.price = price;
        this.bookingStatus = BookingStatus.PENDING;
        this.bookedAt = Instant.now();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFlightId() {
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

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Instant getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Instant bookedAt) {
        this.bookedAt = bookedAt;
    }
}
