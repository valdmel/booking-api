package com.booking.repository;

import com.booking.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<ReservationEntity, Long> {

}