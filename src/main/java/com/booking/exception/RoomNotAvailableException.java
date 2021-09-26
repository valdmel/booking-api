package com.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoomNotAvailableException extends RuntimeException {

    public RoomNotAvailableException() {
        super("The room is not available to another reservation!");
    }
}