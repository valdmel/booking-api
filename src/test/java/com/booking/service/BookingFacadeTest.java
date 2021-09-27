package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.dto.ReservationRequestDTO;
import com.booking.dto.ReservationResponseDTO;
import com.booking.exception.ReservationNotCreatedException;
import com.booking.mapper.ReservationMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingFacadeTest {

	@InjectMocks
	private BookingFacade bookingFacade;

	@Mock
	private BookingService bookingService;

	@Spy
	private final ReservationMapper reservationMapper = ReservationMapper.INSTANCE;

	private Reservation reservation;
	private ReservationRequestDTO reservationRequestDTO;
	private ReservationResponseDTO reservationResponseDTO;

	@BeforeEach
	public void init() {
		reservation = new Reservation();
		reservationRequestDTO = new ReservationRequestDTO();
		reservationResponseDTO = new ReservationResponseDTO();

		reservation.setId(1L);
		reservation.setStartDate(LocalDate.now());
		reservation.setEndDate(LocalDate.now().plusDays(1));

		reservationRequestDTO.setId(1L);
		reservationRequestDTO.setStartDate(LocalDate.now().toString());
		reservationRequestDTO.setEndDate(LocalDate.now().plusDays(1).toString());

		reservationResponseDTO.setId(1L);
		reservationResponseDTO.setStartDate(LocalDate.now().toString());
		reservationResponseDTO.setEndDate(LocalDate.now().plusDays(1).toString());
	}

	@Test
	public void shouldCreateAReservationWithValidDates() {
		when(reservationMapper.mapReservationRequestDTOToReservation(any())).thenReturn(reservation);
		when(bookingService.createReservation(any())).thenReturn(reservation);
		when(reservationMapper.mapReservationToReservationResponseDTO(any())).thenReturn(reservationResponseDTO);

		ReservationResponseDTO reservationDTOResult = bookingFacade.createReservation(reservationRequestDTO);

		verify(bookingService, Mockito.times(1)).createReservation(reservation);
		assertNotNull(reservationDTOResult);
		assertEquals(Long.valueOf(1L), reservationDTOResult.getId());
		assertEquals(LocalDate.now().toString(), reservationDTOResult.getStartDate());
		assertEquals(LocalDate.now().plusDays(1).toString(), reservationDTOResult.getEndDate());
	}

	@Test
	public void shouldThrowAReservationNotCreatedExceptionWhenTryingToCreateAReservationWithInvalidDates() {
		reservationRequestDTO.setEndDate(LocalDate.now().minusDays(3).toString());

		when(reservationMapper.mapReservationRequestDTOToReservation(any())).thenReturn(reservation);
		when(bookingService.createReservation(any())).thenReturn(null);
		when(reservationMapper.mapReservationToReservationResponseDTO(any())).thenReturn(reservationResponseDTO);

		Assertions.assertThrows(ReservationNotCreatedException.class, () -> bookingFacade.createReservation(reservationRequestDTO));

		verify(bookingService, Mockito.times(0)).createReservation(reservation);
	}

	@Test
	public void shouldCheckReservationAvailability() {
		when(reservationMapper.mapReservationToReservationResponseDTO(any())).thenReturn(reservationResponseDTO);
		when(bookingService.checkReservationAvailability()).thenReturn(reservation);

		bookingFacade.checkReservationAvailability();

		verify(bookingService, Mockito.times(1)).checkReservationAvailability();
	}

	@Test
	public void shouldUpdateAReservationWithValidDates() {
		when(reservationMapper.mapReservationRequestDTOToReservation(any())).thenReturn(reservation);
		when(bookingService.updateReservation(any())).thenReturn(reservation);
		when(reservationMapper.mapReservationToReservationResponseDTO(any())).thenReturn(reservationResponseDTO);

		ReservationResponseDTO reservationDTOResult = bookingFacade.updateReservation(reservationRequestDTO);

		verify(bookingService, Mockito.times(1)).updateReservation(reservation);
		assertNotNull(reservationDTOResult);
		assertEquals(Long.valueOf(1L), reservationDTOResult.getId());
		assertEquals(LocalDate.now().toString(), reservationDTOResult.getStartDate());
		assertEquals(LocalDate.now().plusDays(1).toString(), reservationDTOResult.getEndDate());
	}

	@Test
	public void shouldThrowAReservationNotCreatedExceptionWhenTryingToUpdateAReservationWithInvalidDates() {
		reservationRequestDTO.setEndDate(LocalDate.now().minusDays(3).toString());

		when(reservationMapper.mapReservationRequestDTOToReservation(any())).thenReturn(reservation);
		when(bookingService.updateReservation(any())).thenReturn(null);
		when(reservationMapper.mapReservationToReservationResponseDTO(any())).thenReturn(reservationResponseDTO);

		Assertions.assertThrows(ReservationNotCreatedException.class, () -> bookingFacade.updateReservation(reservationRequestDTO));

		verify(bookingService, Mockito.times(0)).updateReservation(reservation);
	}

	@Test
	public void shouldCancelAReservation() {
		doNothing().when(bookingService).cancelReservationById(any());

		bookingFacade.cancelReservationById(1L);

		verify(bookingService, Mockito.times(1)).cancelReservationById(1L);
	}
}