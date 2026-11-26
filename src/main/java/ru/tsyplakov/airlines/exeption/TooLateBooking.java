package ru.tsyplakov.airlines.exeption;

public class TooLateBooking extends RuntimeException {
    public TooLateBooking(String message) {
        super(message);
    }
}
