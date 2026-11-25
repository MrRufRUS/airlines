package ru.tsyplakov.airlines.service;

import org.springframework.stereotype.Service;
import ru.tsyplakov.airlines.domain.Flight;
import ru.tsyplakov.airlines.dto.flight.FlightCreateRequest;
import ru.tsyplakov.airlines.dto.flight.FlightResponse;
import ru.tsyplakov.airlines.dto.flight.FlightUpdateRequest;
import ru.tsyplakov.airlines.exeption.NotFoundException;
import ru.tsyplakov.airlines.repository.FlightRepository;
import ru.tsyplakov.airlines.mapper.FlightMapper;

import java.time.Instant;

@Service
public class FlightService extends BaseService<Flight, FlightResponse, FlightMapper> {

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        super(flightRepository, flightMapper);
    }

    public FlightResponse create(FlightCreateRequest flightCreateRequest) {
        Long id = repository.getNextId();
        Flight flight = new Flight(
                id,
                flightCreateRequest.flightNumber(),
                flightCreateRequest.departureICAO(),
                flightCreateRequest.arrivalICAO(),
                flightCreateRequest.departureTime(),
                flightCreateRequest.arrivalTime(),
                flightCreateRequest.capacity()
        );
        repository.create(flight);
        return mapper.toResponse(flight);
    }

    public FlightResponse update(Long id, FlightUpdateRequest flightUpdateRequest) {
        Flight flightForUpdate = repository.getById(id);
        if (flightForUpdate == null) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        Flight updatedFlight = mapper.updateEntity(flightForUpdate, flightUpdateRequest);
        repository.update(updatedFlight);
        return mapper.toResponse(updatedFlight);
    }
}
