package ru.tsyplakov.airlines.service;

import org.springframework.stereotype.Service;
import ru.tsyplakov.airlines.domain.Booking;
import ru.tsyplakov.airlines.domain.Flight;
import ru.tsyplakov.airlines.domain.User;
import ru.tsyplakov.airlines.dto.booking.BookingCreateRequest;
import ru.tsyplakov.airlines.dto.booking.BookingResponse;
import ru.tsyplakov.airlines.dto.booking.BookingUpdateRequest;
import ru.tsyplakov.airlines.exception.NotEnoughSeatsException;
import ru.tsyplakov.airlines.exception.NotFoundException;
import ru.tsyplakov.airlines.exception.TooLateBookingException;
import ru.tsyplakov.airlines.mapper.BookingMapper;
import ru.tsyplakov.airlines.repository.BookingRepository;
import ru.tsyplakov.airlines.repository.FlightRepository;
import ru.tsyplakov.airlines.repository.UserRepository;

@Service
public class BookingService extends BaseService<Booking, BookingResponse, BookingMapper> {
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    public BookingService(
            BookingRepository bookingRepository,
            BookingMapper bookingMapper,
            FlightRepository flightRepository,
            UserRepository userRepository) {
        super(bookingRepository, bookingMapper);
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    public BookingResponse create(BookingCreateRequest request) {
        Long id = repository.getNextId();
        Flight flight = flightRepository.getById(request.flightId());
        User user = userRepository.getById(request.userId());
        if (flight == null) {
            throw new NotFoundException("Flight with id: " + request.flightId() + " not found");
        }
        if (user == null) {
            throw new NotFoundException("User with id: " + request.userId() + " not found");
        }
        if (flight.getDepartureTime().isBefore(java.time.Instant.now())) {
            throw new TooLateBookingException("Cannot book a flight that has already departed");
        }
        if (getAll().stream()
                        .filter(booking -> booking.flightId().equals(request.flightId()))
                        .count()
                >= flight.getCapacity()) {
            throw new NotEnoughSeatsException(
                    "Not enough seats available for flight with id: " + request.flightId());
        }

        Booking booking = mapper.toEntity(id, request);
        repository.create(booking);
        return mapper.toResponse(booking);
    }

    public BookingResponse update(Long id, BookingUpdateRequest request) {
        Booking bookingForUpdate = repository.getById(id);
        if (bookingForUpdate == null) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        Booking updatedBooking = mapper.updateEntity(bookingForUpdate, request);
        repository.update(updatedBooking);
        return mapper.toResponse(updatedBooking);
    }
}
