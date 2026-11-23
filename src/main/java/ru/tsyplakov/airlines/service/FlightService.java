package ru.tsyplakov.airlines.service;

import org.springframework.stereotype.Service;
import ru.tsyplakov.airlines.domain.Flight;
import ru.tsyplakov.airlines.repository.FlightRepository;

import java.time.Instant;

@Service
public class FlightService extends BaseService<Flight> {

    public FlightService(FlightRepository flightRepository) {
        super(flightRepository);
    }

    public Flight create(
            String flightNumber,
            String departureICAO,
            String arrivalICAO,
            Instant departureTime,
            Instant arrivalTime,
            int capacity
    ) {
        Long id = repository.getNextId();
        Flight flight = new Flight(
                id,
                flightNumber,
                departureICAO,
                arrivalICAO,
                departureTime,
                arrivalTime,
                capacity
        );
        repository.create(flight);
        return flight;
    }
}
