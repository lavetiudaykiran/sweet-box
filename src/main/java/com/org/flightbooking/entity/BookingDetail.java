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
public class BookingDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookingId;

	private long userId;

	private int numberOfPassengers;

	private String date;

	private String flightCode;

}
