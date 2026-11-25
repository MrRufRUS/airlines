package ru.tsyplakov.airlines.service;

import ru.tsyplakov.airlines.domain.Booking;
import ru.tsyplakov.airlines.domain.ServiceClass;
import ru.tsyplakov.airlines.dto.booking.BookingCreateRequest;
import ru.tsyplakov.airlines.dto.booking.BookingResponse;
import ru.tsyplakov.airlines.dto.booking.BookingUpdateRequest;
import ru.tsyplakov.airlines.exeption.NotFoundException;
import ru.tsyplakov.airlines.mapper.BookingMapper;
import ru.tsyplakov.airlines.repository.BookingRepository;

import java.math.BigDecimal;

public class BookingService extends BaseService<Booking, BookingResponse, BookingMapper> {

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        super(bookingRepository, bookingMapper);
    }

    public BookingResponse create(BookingCreateRequest bookingCreateRequest) {
        Long id = repository.getNextId();
        Booking booking = mapper.toEntity(id, bookingCreateRequest);
        repository.create(booking);
        return mapper.toResponse(booking);
    }

    public BookingResponse update(Long id, BookingUpdateRequest bookingUpdateRequest) {
        Booking bookingForUpdate = repository.getById(id);
        if (bookingForUpdate == null) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        Booking updatedBooking = mapper.updateEntity(bookingForUpdate, bookingUpdateRequest);
        repository.update(updatedBooking);
        return mapper.toResponse(updatedBooking);
    }

}
