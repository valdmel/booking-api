package com.booking.controller;

import com.booking.dto.ReservationDTO;
import com.booking.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(BookingController.BASE_URL)
public class BookingController {

    public static final String BASE_URL = "/api/v1/booking";

    @Autowired
    private BookingFacade bookingFacade;

    @PostMapping("/create")
    public void placeReservation(@RequestBody @Valid ReservationDTO reservationDTO) {
        bookingFacade.createReservation(reservationDTO);
    }

    @GetMapping("/find")
    public void findAvailableRoom() {

    }

    @PostMapping("/update")
    public void updateReservation() {

    }

    @DeleteMapping("/cancel")
    public void cancelReservation() {

    }
}