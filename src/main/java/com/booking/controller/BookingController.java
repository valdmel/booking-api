package com.booking.controller;

import com.booking.dto.ReservationResponseDTO;
import com.booking.dto.ReservationRequestDTO;
import com.booking.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(BookingController.BASE_URL)
public class BookingController {

    private static final String CANCEL_MESSAGE = "Reservation canceled!";

    public static final String BASE_URL = "/api/v1/booking";

    @Autowired
    private BookingFacade bookingFacade;

    @PostMapping("/create")
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody @Valid ReservationRequestDTO
                                                                                reservationRequestDTO) {
        return new ResponseEntity<>(bookingFacade.createReservation(reservationRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<ReservationResponseDTO> checkReservationAvailability() {
        return new ResponseEntity<>(bookingFacade.checkReservationAvailability(), HttpStatus.FOUND);
    }

    @PatchMapping("/update")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@RequestBody @Valid ReservationRequestDTO
                                                                                reservationRequestDTO) {
        return new ResponseEntity<>(bookingFacade.updateReservation(reservationRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable("id") @Min(value = 1, message = "Invalid id!")
                                                                                Long id) {
        bookingFacade.cancelReservationById(id);

        return new ResponseEntity<>(CANCEL_MESSAGE, HttpStatus.OK);
    }
}