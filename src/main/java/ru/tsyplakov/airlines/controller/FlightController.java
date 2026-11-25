package ru.tsyplakov.airlines.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsyplakov.airlines.dto.flight.FlightResponse;
import ru.tsyplakov.airlines.service.FlightService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getFlights() {
        return ResponseEntity.ok(flightService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        FlightResponse flightResponse = flightService.getById(id);

        if (flightResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flightResponse);
    }
}
