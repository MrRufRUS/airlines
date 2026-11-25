package ru.tsyplakov.airlines.service;

import ru.tsyplakov.airlines.domain.Booking;
import ru.tsyplakov.airlines.domain.ServiceClass;
import ru.tsyplakov.airlines.repository.BookingRepository;

import java.math.BigDecimal;

public class BookingService extends BaseService<Booking> {
	public BookingService(BookingRepository bookingRepository)
	{
        super(bookingRepository);
    }

    public Booking create(
            Long userId,
            Long flightId,
            String seatNumber,
            ServiceClass serviceClass,
            BigDecimal price
    ) {
        Long id = repository.getNextId();
        Booking booking = new Booking(
                id,
                userId,
                flightId,
                seatNumber,
                serviceClass,
                price
        );
        repository.create(booking);
        return booking;
    }
}
