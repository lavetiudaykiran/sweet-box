package com.org.flightbooking.service;

import com.org.flightbooking.dto.UserDto;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.exception.LoginException;
import com.org.flightbooking.exception.SuccessLoginException;

public interface UserService {
	public UserDto checkLoginByUserId(String emailId, String password) throws CustomException, LoginException,SuccessLoginException;

}
