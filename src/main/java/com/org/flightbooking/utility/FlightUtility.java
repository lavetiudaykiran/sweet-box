package com.org.flightbooking.utility;

public class FlightUtility {
	
	 private FlightUtility() {
		    throw new IllegalStateException("Utility class");
		  }
	
	
	
	public static final String FLIGHTS_NOT_AVAILABLE = "Flights are not available for this particular date";
	
	public static final String USER_ERROR = "EmailId is not exsist";

	public static final String P_ERROR = "Password is Incorrect";

	public static final String NOT_REGISTER_ERROR = " User is not Registered";
	public static final String LIST_EMPTY = "No Data Available/ Retrieved";
	public static final String DAY_BEFORE_TODAY = "Should not enter Previous date";
	public static final String SUCCESS_LOGIN = "SUCCESSFULLY LOGGED IN";
	
}
