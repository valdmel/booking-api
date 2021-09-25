package com.booking.mapper;

import com.booking.domain.Reservation;
import com.booking.dto.ReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    Reservation mapReservationDTOToReservation(ReservationDTO reservationDTO);
}