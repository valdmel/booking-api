package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.entity.ReservationEntity;
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
        boolean isRoomAvailable = bookingRepository.count() > 0;

        if (isRoomAvailable) {
            throw new RoomNotAvailableException();
        }

        ReservationEntity reservationEntity = Mappers.getMapper(ReservationMapper.class).mapReservationToReservationEntity(reservation);

        return Mappers.getMapper(ReservationMapper.class).mapReservationEntityToReservation(bookingRepository.save(reservationEntity));
    }
}