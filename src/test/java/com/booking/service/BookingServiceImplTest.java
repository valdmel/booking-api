package com.booking.service;

import com.booking.domain.Reservation;
import com.booking.dto.ReservationRequestDTO;
import com.booking.dto.ReservationResponseDTO;
import com.booking.entity.ReservationEntity;
import com.booking.exception.ReservationNotCreatedException;
import com.booking.exception.ReservationNotFoundException;
import com.booking.exception.RoomNotAvailableException;
import com.booking.mapper.ReservationMapper;
import com.booking.repository.BookingRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingServiceImplTest {

	@InjectMocks
	private BookingService bookingService = new BookingServiceImpl();

	@Mock
	private BookingRepository bookingRepository;

	@Spy
	private final ReservationMapper reservationMapper = ReservationMapper.INSTANCE;

	private Reservation reservation;
	private ReservationEntity reservationEntity;
	private List<ReservationEntity> reservationEntities;

	@BeforeEach
	public void init() {
		reservation = new Reservation();
		reservationEntity = new ReservationEntity();

		reservation.setId(1L);
		reservation.setStartDate(LocalDate.now());
		reservation.setEndDate(LocalDate.now().plusDays(1));

		reservationEntity.setId(1L);
		reservationEntity.setStartDate(LocalDate.now());
		reservationEntity.setEndDate(LocalDate.now().plusDays(1));

		reservationEntities = Arrays.asList(reservationEntity);
	}

	@Test
	public void shouldCreateAReservation() {
		when(bookingRepository.count()).thenReturn(0L);
		when(reservationMapper.mapReservationToReservationEntity(any())).thenReturn(reservationEntity);
		when(bookingRepository.save(any())).thenReturn(reservationEntity);
		when(reservationMapper.mapReservationEntityToReservation(any())).thenReturn(reservation);

		Reservation reservationResult = bookingService.createReservation(reservation);

		verify(bookingRepository, Mockito.times(1)).save(reservationEntity);
		assertNotNull(reservationResult);
		assertEquals(Long.valueOf(1L), reservationEntity.getId());
		assertEquals(LocalDate.now(), reservationEntity.getStartDate());
		assertEquals(LocalDate.now().plusDays(1), reservationEntity.getEndDate());
	}

	@Test
	public void shouldThrowARoomNotAvailableExceptionWhenTryingToCreateAReservation() {
		when(bookingRepository.count()).thenReturn(1L);

		Assertions.assertThrows(RoomNotAvailableException.class, () -> bookingService.createReservation(reservation));

		verify(bookingRepository, Mockito.times(0)).save(reservationEntity);
	}

	@Test
	public void shouldCheckReservationAvailability() {
		when(bookingRepository.count()).thenReturn(1L);
		when(bookingRepository.findAll()).thenReturn(reservationEntities);
		when(reservationMapper.mapReservationEntityToReservation(any())).thenReturn(reservation);

		Reservation reservation = bookingService.checkReservationAvailability();

		verify(bookingRepository, Mockito.times(1)).findAll();
		assertNotNull(reservation);
		assertEquals(Long.valueOf(1L), reservationEntities.get(0).getId());
		assertEquals(LocalDate.now(), reservationEntities.get(0).getStartDate());
		assertEquals(LocalDate.now().plusDays(1), reservationEntities.get(0).getEndDate());
	}

	@Test
	public void shouldThrowAReservationNotFoundExceptionWhenTryingToCheckForReservationAvailability() {
		when(bookingRepository.count()).thenReturn(0L);

		Assertions.assertThrows(ReservationNotFoundException.class, () -> bookingService.checkReservationAvailability());

		verify(bookingRepository, Mockito.times(0)).findAll();
	}

	@Test
	public void shouldUpdateAReservation() {
		when(bookingRepository.count()).thenReturn(1L);
		when(reservationMapper.mapReservationToReservationEntity(any())).thenReturn(reservationEntity);
		when(bookingRepository.save(any())).thenReturn(reservationEntity);
		when(reservationMapper.mapReservationEntityToReservation(any())).thenReturn(reservation);

		Reservation reservationResult = bookingService.updateReservation(reservation);

		verify(bookingRepository, Mockito.times(1)).save(reservationEntity);
		assertNotNull(reservationResult);
		assertEquals(Long.valueOf(1L), reservationEntity.getId());
		assertEquals(LocalDate.now(), reservationEntity.getStartDate());
		assertEquals(LocalDate.now().plusDays(1), reservationEntity.getEndDate());
	}

	@Test
	public void shouldThrowAReservationNotFoundExceptionWhenTryingToUpdateAReservation() {
		when(bookingRepository.count()).thenReturn(0L);

		Assertions.assertThrows(ReservationNotFoundException.class, () -> bookingService.updateReservation(reservation));

		verify(bookingRepository, Mockito.times(0)).save(reservationEntity);
	}

	@Test
	public void shouldCancelAReservation() {
		when(bookingRepository.count()).thenReturn(1L);
		doNothing().when(bookingRepository).deleteById(any());

		bookingService.cancelReservationById(1L);

		verify(bookingRepository, Mockito.times(1)).deleteById(1L);
	}

	@Test
	public void shouldThrowAReservationNotFoundExceptionWhenTryingToCancelAReservation() {
		when(bookingRepository.count()).thenReturn(0L);

		Assertions.assertThrows(ReservationNotFoundException.class, () -> bookingService.cancelReservationById(1L));

		verify(bookingRepository, Mockito.times(0)).deleteById(1L);
	}
}