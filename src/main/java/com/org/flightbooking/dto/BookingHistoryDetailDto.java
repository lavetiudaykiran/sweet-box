package com.org.flightbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingHistoryDetailDto {

	private long userId;
	private String flightCode;
	private String date;
	private int numberOfPassengers;

}
