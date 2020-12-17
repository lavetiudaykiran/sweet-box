package com.org.flightbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDetailsHistoryResponseDto {

	private long userId;

	private int numberOfPassengers;

	private String date;

	private String flightCode;
}
