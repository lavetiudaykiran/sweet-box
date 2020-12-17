package com.org.flightbooking.service;

import java.text.ParseException;
import java.util.List;

import com.org.flightbooking.dto.BookingDetailsHistoryResponseDto;
import com.org.flightbooking.dto.FlightDetailsRequestDto;
import com.org.flightbooking.dto.FlightDetailsResponseDto;
import com.org.flightbooking.dto.PassengerDetailsRequestDto;
import com.org.flightbooking.exception.CustomException;

public interface FlightService {

	List<FlightDetailsResponseDto> searchFlightDetails(FlightDetailsRequestDto flightDetailsRequestDto)
			throws CustomException, ParseException;

	String bookingRequest(long flightId, long userId, int numberOfPassengerss) throws CustomException;

	String passengerDetails(List<PassengerDetailsRequestDto> listOfPassengerDetailsRequestDto) throws CustomException;

	List<BookingDetailsHistoryResponseDto> getBookingHistory(long userId) throws CustomException;

}
