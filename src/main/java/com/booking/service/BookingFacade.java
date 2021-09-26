package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.dto.ReservationResponseDTO;
import com.booking.dto.ReservationRequestDTO;
import com.booking.exception.ReservationNotCreatedException;
import com.booking.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingFacade {

    @Autowired
    private BookingService bookingService;

    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        Reservation reservation = ReservationMapper.INSTANCE.mapReservationRequestDTOToReservation(reservationRequestDTO);

        if (!reservation.canBeCompleted()) {
            throw new ReservationNotCreatedException();
        }

        return ReservationMapper.INSTANCE.mapReservationToReservationDTO(bookingService.createReservation(reservation));
    }

    public ReservationResponseDTO checkReservationAvailability() {
        return ReservationMapper.INSTANCE.mapReservationToReservationDTO(bookingService.checkReservationAvailability());
    }
}