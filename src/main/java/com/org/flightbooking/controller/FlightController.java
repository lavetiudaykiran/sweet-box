package com.org.flightbooking.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.flightbooking.dto.BookingDetailsHistoryResponseDto;
import com.org.flightbooking.dto.FlightDetailsRequestDto;
import com.org.flightbooking.dto.FlightDetailsResponseDto;
import com.org.flightbooking.dto.PassengerDetailsRequestDto;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	FlightService flightService;

	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

	/**
	 * 
	 * @param flightDetailsRequestDto
	 * @return
	 * @throws CustomException
	 * @throws ParseException
	 * @author Suprita returns List of available flights for entered date This
	 *         method gives us List of Flights available for requested date,
	 *         requested source and destination
	 */
	@GetMapping("/")
	public ResponseEntity<List<FlightDetailsResponseDto>> searchFlightDetails(
			FlightDetailsRequestDto flightDetailsRequestDto) throws CustomException, ParseException {
		logger.info("searchFlightDetails method of FlightCntroller class");
		return new ResponseEntity<>(flightService.searchFlightDetails(flightDetailsRequestDto), HttpStatus.OK);
	}

	/**
	 * 
	 * @param flightId
	 * @param userId
	 * @param numberOfPassengers
	 * @return
	 * @throws CustomException when we book flight along with flightId we are
	 *                         sending the number of passengers we want to book a
	 *                         flight
	 */
	@GetMapping("/{flightId}/users/{userId}/booking")
	public ResponseEntity<String> bookingRequest(@PathVariable("flightId") long flightId,
			@PathVariable("userId") long userId, @RequestParam("numberOfPassengers") int numberOfPassengers)
			throws CustomException {
		return new ResponseEntity<>(flightService.bookingRequest(flightId, userId, numberOfPassengers), HttpStatus.OK);
	}

	/**
	 * 
	 * @param listPassengerDetailsRequestDto
	 * @return
	 * @throws CustomException
	 */

	@PostMapping("/passengerdetails")
	public ResponseEntity<String> passengerDetails(
			@RequestBody List<PassengerDetailsRequestDto> listPassengerDetailsRequestDto) throws CustomException {
		return new ResponseEntity<>(flightService.passengerDetails(listPassengerDetailsRequestDto), HttpStatus.OK);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 * @throws CustomException
	 */
	@GetMapping(value = "{userId}/history")
	public ResponseEntity<List<BookingDetailsHistoryResponseDto>> getBookingHistory(@PathVariable long userId)
			throws CustomException {
		return new ResponseEntity<>(flightService.getBookingHistory(userId), HttpStatus.OK);

	}

	@GetMapping(value = "{userId}/history")
	public ResponseEntity<List<BookingDetailsHistoryResponseDto>> getBookingHistory1(@PathVariable long userId)
			throws CustomException {
		return new ResponseEntity<>(flightService.getBookingHistory(userId), HttpStatus.OK);

	}

}
