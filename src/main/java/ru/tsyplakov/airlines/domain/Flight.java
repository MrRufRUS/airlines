package ru.tsyplakov.airlines.domain;

import java.time.Instant;

public class Flight extends BaseEntity {
    private String flightNumber;
    private String departureICAO;
    private String arrivalICAO;
    private Instant departureTime; // время по UTC
    private Instant arrivalTime; // время по UTC

    public Flight(Long id, String flightNumber, String departureICAO, String arrivalICAO, Instant departureTime, Instant arrivalTime) {
        super(id);
        this.flightNumber = flightNumber;
        this.departureICAO = departureICAO;
        this.arrivalICAO = arrivalICAO;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureICAO() {
        return departureICAO;
    }

    public void setDepartureICAO(String departureICAO) {
        this.departureICAO = departureICAO;
    }

    public String getArrivalICAO() {
        return arrivalICAO;
    }

    public void setArrivalICAO(String arrivalICAO) {
        this.arrivalICAO = arrivalICAO;
    }

    public Instant getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Instant departureTime) {
        this.departureTime = departureTime;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Instant arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
