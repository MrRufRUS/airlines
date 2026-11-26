package ru.tsyplakov.airlines.exception;

public class TooLateBookingException extends RuntimeException {
    public TooLateBookingException(String message) {
        super(message);
    }
}
