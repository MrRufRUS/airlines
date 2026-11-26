package ru.tsyplakov.airlines.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tsyplakov.airlines.exeption.NotEnoughSeats;
import ru.tsyplakov.airlines.exeption.NotFoundException;
import ru.tsyplakov.airlines.exeption.TooLateBooking;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");

        return problemDetail;
    }

    @ExceptionHandler(NotEnoughSeats.class)
    public ProblemDetail handleNotEnoughSeatsException(NotEnoughSeats ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Not Enough Seats");

        return problemDetail;
    }

    @ExceptionHandler(TooLateBooking.class)
    public ProblemDetail handleTooLateBookingException(TooLateBooking ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Too Late to Book");

        return problemDetail;
    }
}
