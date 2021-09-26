package com.booking.exception;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException() {
        super("No reservation could be found!");
    }
}