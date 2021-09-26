package com.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException() {
        super("No reservation could be found!");
    }
}