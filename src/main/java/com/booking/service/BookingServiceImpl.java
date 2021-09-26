package com.booking.service;

import com.booking.commons.enums.ReservationStatus;
import com.booking.domain.Reservation;
import com.booking.entity.ReservationEntity;
import com.booking.exception.ReservationNotFoundException;
import com.booking.exception.RoomNotAvailableException;
import com.booking.mapper.ReservationMapper;
import com.booking.repository.BookingRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        boolean isThereAnyReservation = bookingRepository.count() > 0;

        if (isThereAnyReservation) {
            throw new RoomNotAvailableException();
        }

        reservation.setStatus(ReservationStatus.NOT_AVAILABLE.toString());

        ReservationEntity reservationEntity = Mappers.getMapper(ReservationMapper.class).mapReservationToReservationEntity(reservation);

        return ReservationMapper.INSTANCE.mapReservationEntityToReservation(bookingRepository.save(reservationEntity));
    }

    @Override
    public Reservation findReservationAvailability() {
        boolean isThereAnyReservation = bookingRepository.count() > 0;

        if (!isThereAnyReservation) {
            throw new ReservationNotFoundException();
        }

        return ReservationMapper.INSTANCE.mapReservationEntityToReservation(bookingRepository.findAll().get(0));
    }
}