package ru.tsyplakov.airlines.service;

import org.springframework.stereotype.Service;
import ru.tsyplakov.airlines.domain.Flight;
import ru.tsyplakov.airlines.dto.flight.FlightCreateRequest;
import ru.tsyplakov.airlines.dto.flight.FlightResponse;
import ru.tsyplakov.airlines.dto.flight.FlightUpdateRequest;
import ru.tsyplakov.airlines.exception.NotFoundException;
import ru.tsyplakov.airlines.mapper.FlightMapper;
import ru.tsyplakov.airlines.repository.FlightRepository;

@Service
public class FlightService extends BaseService<Flight, FlightResponse, FlightMapper> {

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        super(flightRepository, flightMapper);
    }

    public FlightResponse create(FlightCreateRequest request) {
        Long id = repository.getNextId();
        Flight flight = mapper.toEntity(id, request);
        repository.create(flight);
        return mapper.toResponse(flight);
    }

    public FlightResponse update(Long id, FlightUpdateRequest request) {
        Flight flightForUpdate = repository.getById(id);
        if (flightForUpdate == null) {
            throw new NotFoundException("Entity with id: " + id + " not found");
        }
        Flight updatedFlight = mapper.updateEntity(flightForUpdate, request);
        repository.update(updatedFlight);
        return mapper.toResponse(updatedFlight);
    }
}
