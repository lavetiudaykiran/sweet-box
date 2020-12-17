package com.org.flightbooking.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.flightbooking.dto.UserDto;
import com.org.flightbooking.entity.UserDetail;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.exception.LoginException;
import com.org.flightbooking.exception.SuccessLoginException;
import com.org.flightbooking.repository.UserRepository;
import com.org.flightbooking.utility.FlightUtility;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	long loggedInUserId;

	public long getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(long loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	@Autowired
	HttpSession session;

	@Override
	public UserDto checkLoginByUserId(String emailId, String password)
			throws CustomException, LoginException, SuccessLoginException {

		logger.info("Entered the checkLoginByUserId method");

		/**
		 * This method is used for checking the login credentials of user by emailId and
		 * password
		 *
		 * @author Pallavi
		 * @since 2020-03-12
		 *
		 * @throws CustomException       -Thrown when emailId not valid or not found
		 * @throws LoginException        -Thrown when password not valid
		 * @throws SuccessLoginException -Thrown when login and password correct and
		 *                               success login
		 *
		 * @return login successful message and status code
		 */
		Optional<UserDetail> userDetail = userRepository.findByEmailId(emailId);
		
		if (!userDetail.isPresent()) {
			logger.info("User is not registered");
			throw new CustomException(FlightUtility.NOT_REGISTER_ERROR);
		} else {
			logger.info("checking for userDetail present or not");
			if (!(userDetail.get().getEmailId().equals(emailId))) {
				logger.info("Checking for user email Id id equal to userDto emailId");
				throw new CustomException(FlightUtility.USER_ERROR);
			} else {
				try {
					if (userDetail.get().getPassword().equals(password)) {
						logger.info("Successfully Logged In");
						throw new SuccessLoginException(FlightUtility.SUCCESS_LOGIN);

					} else {
						logger.info("Password is incorrect");
						throw new LoginException(FlightUtility.P_ERROR);
					}
				} catch (LoginException ex) {
					logger.info("Password is incorrect");
					throw new LoginException(FlightUtility.P_ERROR);
				}
			}
		}
	}
}