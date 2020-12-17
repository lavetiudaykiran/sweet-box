package com.org.flightbooking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlightDetailsResponseDto {

	private String flightCode;
	private String flightName;
	private String source;
	private String destination;
	private String fromTime;
	private String toTime;
	private int seatsAvailable;
	private List<ChargeDetailsResponseDto> chargeDetailsResponseDto;

}
