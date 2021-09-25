package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.dto.ReservationDTO;
import com.booking.mapper.ReservationMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class BookingFacade {

    public void createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = Mappers.getMapper(ReservationMapper.class).mapReservationDTOToReservation(reservationDTO);
    }
}