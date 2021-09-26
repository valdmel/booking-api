package com.booking.mapper;

import com.booking.domain.Reservation;
import com.booking.dto.ReservationResponseDTO;
import com.booking.dto.ReservationRequestDTO;
import com.booking.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation mapReservationRequestDTOToReservation(ReservationRequestDTO reservationRequestDTO);

    Reservation mapReservationEntityToReservation(ReservationEntity reservationEntity);

    ReservationEntity mapReservationToReservationEntity(Reservation reservation);

    ReservationResponseDTO mapReservationToReservationResponseDTO(Reservation reservation);
}
