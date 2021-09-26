package com.booking.exception;

public class ReservationNotCreatedException extends RuntimeException {

    public ReservationNotCreatedException() {
        super("The stay can’t be longer than 3 days and can’t be reserved more than 30 days in advance!");
    }
}