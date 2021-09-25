package com.booking.commons.enums;

public enum ReservationStatus {
    AVAILABLE("Available"),
    NOT_AVAILABLE("Not Available");

    private String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
