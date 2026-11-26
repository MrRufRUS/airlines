package ru.tsyplakov.airlines.exeption;

public class NotEnoughSeats extends RuntimeException {
    public NotEnoughSeats(String message) {
        super(message);
    }
}
