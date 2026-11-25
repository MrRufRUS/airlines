package ru.tsyplakov.airlines.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tsyplakov.airlines.dto.flight.FlightCreateRequest;
import ru.tsyplakov.airlines.service.FlightService;

import java.time.Instant;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initFlights(FlightService flightService) {
        return args -> {
            flightService.create(new FlightCreateRequest(
                    "RA123",
                    "UUEE",
                    "ULLI",
                    Instant.parse("2026-12-01T10:00:00Z"),
                    Instant.parse("2026-12-01T11:30:00Z"),
                    180
                )
            );

            flightService.create(new FlightCreateRequest(
                    "RA456",
                    "ULLI",
                    "UUEE",
                    Instant.parse("2026-12-02T12:00:00Z"),
                    Instant.parse("2026-12-02T13:30:00Z"),
                    180
                )
            );

            flightService.create(new FlightCreateRequest(
                    "RA789",
                    "UUEE",
                    "USSS",
                    Instant.parse("2026-12-03T08:00:00Z"),
                    Instant.parse("2026-12-03T10:30:00Z"),
                    220
                )
            );

            flightService.create(new FlightCreateRequest(
                    "RA101",
                    "UHWW",
                    "UUEE",
                    Instant.parse("2026-12-04T03:00:00Z"),
                    Instant.parse("2026-12-04T11:30:00Z"),
                    300
                )
            );

            flightService.create(new FlightCreateRequest(
                    "RA202",
                    "USSS",
                    "ULLI",
                    Instant.parse("2026-12-05T06:00:00Z"),
                    Instant.parse("2026-12-05T09:00:00Z"),
                    150
                )
            );
        };
    }
}