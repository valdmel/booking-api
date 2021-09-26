package com.booking.exception;

public class RoomNotAvailableException extends RuntimeException {

    public RoomNotAvailableException() {
        super("Room is not available for another reservation!");
    }
}