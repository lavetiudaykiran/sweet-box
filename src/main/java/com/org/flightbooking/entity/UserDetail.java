package com.org.flightbooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	private String userName;

	@Column(unique = true)
	private String emailId;

	private String password;

	private int age;

	private String sex;

	private String mobileNumber;

}
