package com.org.flightbooking.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PassengerDetailsRequestDto {
	private String name;

	private int age;

	private String sex;
	private String mealPreference;
}
