package com.org.flightbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PassengerDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long passengerId;

	private long bookingId;

	private String name;

	private int age;

	private String sex;
	private String mealPreference;

}
