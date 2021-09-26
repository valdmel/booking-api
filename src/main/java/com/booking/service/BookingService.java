package com.booking.service;

import com.booking.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {
    Reservation createReservation(Reservation reservation);

    Reservation checkReservationAvailability();
}