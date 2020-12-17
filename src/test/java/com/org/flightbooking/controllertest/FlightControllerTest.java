package com.org.flightbooking.controllertest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.org.flightbooking.controller.FlightController;
import com.org.flightbooking.dto.BookingDetailsHistoryResponseDto;
import com.org.flightbooking.dto.FlightDetailsRequestDto;
import com.org.flightbooking.dto.FlightDetailsResponseDto;
import com.org.flightbooking.dto.PassengerDetailsRequestDto;
import com.org.flightbooking.entity.FlightDetail;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.service.FlightService;

@RunWith(MockitoJUnitRunner.class)
public class FlightControllerTest {

	@Mock
	FlightService flightService;

	@InjectMocks
	FlightController flightController;

	FlightDetail flightDetail;

	FlightDetailsRequestDto flightDetailsRequestDto = new FlightDetailsRequestDto();

	List<FlightDetailsResponseDto> flightDetailsResponseDto;
	List<PassengerDetailsRequestDto> listOfPassengerDetailsRequestDto;
	List<BookingDetailsHistoryResponseDto> listBookingDetailsHistoryResponseDto;
	long flightId;
	long userId;
	int numberOfPassengers;

	@Before
	public void setup() {
		flightDetail = new FlightDetail();
		flightDetail.setAvailability("YES");
		flightDetail.setDay("MON");
		flightDetail.setDestination("CHENNAI");
		flightDetail.setFlightCode("AIRBLR2CH");
		flightDetail.setFlightId(1l);
		flightDetail.setFlightName("AIR INDIA");
		flightDetail.setFromTime("15:00:00");
		flightDetail.setSeatsAvailable(10);
		flightDetail.setSource("Bangalore");
		flightDetail.setToTime("17:00:00");

		flightDetailsResponseDto = new ArrayList<>();
		flightDetailsRequestDto.setDate("2020-03-10");
		flightDetailsRequestDto.setDestination("CHENNAI");

		flightDetailsRequestDto.setSource("BANGALORE");
		flightId = 1l;
		userId = 1l;
		numberOfPassengers = 1;

		listOfPassengerDetailsRequestDto = new ArrayList<>();
	}

	@Test
	public void searchFlightDetails() throws CustomException, ParseException {
		Mockito.when(flightService.searchFlightDetails(Mockito.any())).thenReturn(flightDetailsResponseDto);

		ResponseEntity<List<FlightDetailsResponseDto>> response = flightController
				.searchFlightDetails(flightDetailsRequestDto);

		Assert.assertNotNull(response.getBody());

		Assert.assertEquals(flightDetailsResponseDto, response.getBody());
	}

	@Test
	public void bookingRequest() throws CustomException {
		String data = "Enter Passenger Details";
		Mockito.when(flightService.bookingRequest(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt()))
				.thenReturn(data);

		ResponseEntity<String> response = flightController.bookingRequest(flightId, userId, numberOfPassengers);

		Assert.assertNotNull(response.getBody());

		Assert.assertEquals(data, response.getBody());
	}

	@Test
	public void passengerDetails() throws CustomException {
		String data = "Booked Successfully";
		Mockito.when(flightService.passengerDetails(Mockito.any())).thenReturn(data);
		List<PassengerDetailsRequestDto> listPassengerDetailsRequestDto = new ArrayList<>();
		ResponseEntity<String> response = flightController.passengerDetails(listPassengerDetailsRequestDto);

		Assert.assertNotNull(response.getBody());

		Assert.assertEquals(data, response.getBody());
	}

	@Test
	public void getBookingHistory() throws CustomException {
		List<BookingDetailsHistoryResponseDto> bookingHistroyList = new ArrayList<>();

		Mockito.when(flightService.getBookingHistory(Mockito.anyLong())).thenReturn(bookingHistroyList);

		ResponseEntity<List<BookingDetailsHistoryResponseDto>> response = flightController.getBookingHistory(userId);

		Assert.assertNotNull(response.getBody());

		Assert.assertEquals(bookingHistroyList, response.getBody());
	}

}
