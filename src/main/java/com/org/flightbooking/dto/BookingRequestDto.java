package com.org.flightbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter

@Getter
public class BookingRequestDto {
	private long userId;
	private long flightId;
	private int numberOfPassengers;

}
