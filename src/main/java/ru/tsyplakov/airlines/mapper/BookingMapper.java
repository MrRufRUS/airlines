package ru.tsyplakov.airlines.mapper;

import org.springframework.stereotype.Component;
import ru.tsyplakov.airlines.domain.Booking;
import ru.tsyplakov.airlines.dto.booking.BookingCreateRequest;
import ru.tsyplakov.airlines.dto.booking.BookingResponse;
import ru.tsyplakov.airlines.dto.booking.BookingUpdateRequest;

@Component
public class BookingMapper implements ToResponseMapper<Booking, BookingResponse> {

    public Booking toEntity(Long id, BookingCreateRequest bookingCreateRequest) {
        return new Booking(
                id,
                bookingCreateRequest.userId(),
                bookingCreateRequest.flightId(),
                bookingCreateRequest.seatNumber(),
                bookingCreateRequest.serviceClass(),
                bookingCreateRequest.price()
        );
    }

    @Override
    public BookingResponse toResponse(Booking entity) {
        return new BookingResponse(
                entity.getId(),
                entity.getFlightId(),
                entity.getUserId(),
                entity.getSeatNumber(),
                entity.getServiceClass(),
                entity.getPrice(),
                entity.getBookingStatus(),
                entity.getBookedAt()
        );
    }

    public Booking updateEntity(Booking booking, BookingUpdateRequest bookingUpdateRequest) {
        if (bookingUpdateRequest.seatNumber() != null) {
            booking.setSeatNumber(bookingUpdateRequest.seatNumber());
        }
        if (bookingUpdateRequest.serviceClass() != null) {
            booking.setServiceClass(bookingUpdateRequest.serviceClass());
        }
        if (bookingUpdateRequest.price() != null) {
            booking.setPrice(bookingUpdateRequest.price());
        }
        return booking;
    }
}
