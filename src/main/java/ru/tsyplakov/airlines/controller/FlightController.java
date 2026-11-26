package ru.tsyplakov.airlines.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsyplakov.airlines.dto.flight.FlightCreateRequest;
import ru.tsyplakov.airlines.dto.flight.FlightResponse;
import ru.tsyplakov.airlines.dto.flight.FlightUpdateRequest;
import ru.tsyplakov.airlines.service.FlightService;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FlightResponse>> getFlights() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        FlightResponse response = service.getById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(
            @Valid @RequestBody FlightCreateRequest flightCreateRequest) {
        FlightResponse response = service.create(flightCreateRequest);
        URI location = URI.create("/api/v1/flights/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(
            @PathVariable Long id, @Valid @RequestBody FlightUpdateRequest flightUpdateRequest) {
        FlightResponse response = service.update(id, flightUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
