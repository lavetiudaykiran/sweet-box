package com.org.flightbooking.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.flightbooking.dto.UserDto;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.exception.LoginException;
import com.org.flightbooking.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
			
	@Autowired
	private UserService userService;

	/**
	 * 
	 *
	 * @param emailId
	 * @param password
	 * @return ResponseEntity Object along with status code
	 * @throws CustomException
	 * @throws LoginException
	 * @throws LoginException
	 * @throws com.org.flightbooking.exception.SuccessLoginException 
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<UserDto> checkLoginByUserId(@RequestParam("emailId") String emailId,
	@RequestParam("password") String password) throws CustomException, LoginException, com.org.flightbooking.exception.SuccessLoginException {

	logger.info("Entered the checkLoginByUserId method: checking by emailId and password");
	UserDto userDto = userService.checkLoginByUserId(emailId, password);
	return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	}

}
