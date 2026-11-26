package ru.tsyplakov.airlines.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tsyplakov.airlines.exception.NotEnoughSeatsException;
import ru.tsyplakov.airlines.exception.NotFoundException;
import ru.tsyplakov.airlines.exception.TooLateBookingException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Not Found");

        return problemDetail;
    }

    @ExceptionHandler(NotEnoughSeatsException.class)
    public ProblemDetail handleNotEnoughSeatsException(NotEnoughSeatsException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Not Enough Seats");

        return problemDetail;
    }

    @ExceptionHandler(TooLateBookingException.class)
    public ProblemDetail handleTooLateBookingException(TooLateBookingException ex) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Too Late to Book");

        return problemDetail;
    }
}
