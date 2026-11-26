package ru.tsyplakov.airlines.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tsyplakov.airlines.dto.booking.BookingCreateRequest;
import ru.tsyplakov.airlines.dto.booking.BookingResponse;
import ru.tsyplakov.airlines.dto.booking.BookingUpdateRequest;
import ru.tsyplakov.airlines.service.BookingService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@Valid @RequestBody BookingCreateRequest booking) {
        BookingResponse response = service.create(booking);
        URI location = URI.create("/api/v1/bookings/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookingResponse> update(@PathVariable Long id, @Valid @RequestBody BookingUpdateRequest booking) {
        return ResponseEntity.ok(service.update(id, booking));
    }
}
