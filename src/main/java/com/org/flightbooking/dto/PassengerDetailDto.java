package com.org.flightbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PassengerDetailDto {
	
	private long userId;

	private String flightCode;

	private String name;

	private int age;

	private String sex;

	private String date;

	private int numberOfPassengers;

}
