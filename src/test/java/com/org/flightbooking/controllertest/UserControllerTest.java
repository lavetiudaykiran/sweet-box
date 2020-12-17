package com.org.flightbooking.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.flightbooking.controller.UserController;
import com.org.flightbooking.dto.UserDto;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.exception.LoginException;
import com.org.flightbooking.exception.SuccessLoginException;
import com.org.flightbooking.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {
	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	UserDto userDto = new UserDto();

	@Before
	public void setup() {
		userDto.setEmailId("koushik@gmail.com");
		userDto.setPassword("koushik@123");

	}

	@Test
	public void loginTest() throws CustomException, LoginException, SuccessLoginException {
		when(userService.checkLoginByUserId(userDto.getEmailId(), userDto.getPassword())).thenReturn(userDto);
		ResponseEntity<UserDto> actual = userController.checkLoginByUserId(userDto.getEmailId(), userDto.getPassword());
		assertEquals(HttpStatus.ACCEPTED, actual.getStatusCode());

		
	}

}
