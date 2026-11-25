package ru.tsyplakov.airlines.mapper;

import org.springframework.stereotype.Component;
import ru.tsyplakov.airlines.domain.Flight;
import ru.tsyplakov.airlines.dto.flight.FlightCreateRequest;
import ru.tsyplakov.airlines.dto.flight.FlightResponse;
import ru.tsyplakov.airlines.dto.flight.FlightUpdateRequest;

@Component
public class FlightMapper implements ToResponseMapper<Flight, FlightResponse> {

    public Flight toEntity(Long id, FlightCreateRequest flightCreateRequest) {
        return new Flight(
                id,
                flightCreateRequest.flightNumber(),
                flightCreateRequest.departureICAO(),
                flightCreateRequest.arrivalICAO(),
                flightCreateRequest.departureTime(),
                flightCreateRequest.arrivalTime(),
                flightCreateRequest.capacity()
        );
    }

    @Override
    public FlightResponse toResponse(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getDepartureICAO(),
                flight.getArrivalICAO(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getCapacity()
        );
    }

    public Flight updateEntity(Flight flight, FlightUpdateRequest flightCreateRequest) {
        if (flightCreateRequest.flightNumber() != null) {
            flight.setFlightNumber(flightCreateRequest.flightNumber());
        }
        if (flightCreateRequest.departureICAO() != null) {
            flight.setDepartureICAO(flightCreateRequest.departureICAO());
        }
        if (flightCreateRequest.arrivalICAO() != null) {
            flight.setArrivalICAO(flightCreateRequest.arrivalICAO());
        }
        if (flightCreateRequest.departureTime() != null) {
            flight.setDepartureTime(flightCreateRequest.departureTime());
        }
        if (flightCreateRequest.arrivalTime() != null) {
            flight.setArrivalTime(flightCreateRequest.arrivalTime());
        }
        if (flightCreateRequest.capacity() != null) {
            flight.setCapacity(flightCreateRequest.capacity());
        }
        return flight;
    }
}
