package com.org.flightbooking.servicetest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.org.flightbooking.dto.BookingDetailsHistoryResponseDto;
import com.org.flightbooking.dto.FlightDetailsRequestDto;
import com.org.flightbooking.dto.FlightDetailsResponseDto;
import com.org.flightbooking.dto.PassengerDetailsRequestDto;
import com.org.flightbooking.entity.BookingDetail;
import com.org.flightbooking.entity.ChargeDetail;
import com.org.flightbooking.entity.FlightDetail;
import com.org.flightbooking.entity.PassengerDetail;
import com.org.flightbooking.entity.UserDetail;
import com.org.flightbooking.exception.CustomException;
import com.org.flightbooking.repository.BookingDetailRepository;
import com.org.flightbooking.repository.ChargeDetailRepository;
import com.org.flightbooking.repository.FlightDetailRepository;
import com.org.flightbooking.repository.PassengerDetailRepository;
import com.org.flightbooking.repository.UserRepository;
import com.org.flightbooking.service.FlightServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

	@Mock
	FlightDetailRepository flightDetailRepository;

	@Mock
	ChargeDetailRepository chargeDetailRepository;

	@Mock
	BookingDetailRepository bookingDetailRepository;

	@Mock
	PassengerDetailRepository passengerDetailRepository;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	FlightServiceImpl flightServiceImpl;

	FlightDetailsRequestDto flightDetailsRequestDto;
	List<FlightDetailsResponseDto> listOfFlightDetailsResponseDto;
	List<FlightDetail> listOflightDetail;
	List<ChargeDetail> listOfChargeDetail;
	FlightDetail flightDetail;
	ChargeDetail chargeDetail;
	UserDetail userDetail;
	List<BookingDetail> listOfBookingDetail;
	List<PassengerDetailsRequestDto> passengerDetailsRequestDto;;

	BookingDetailsHistoryResponseDto bookingDetailsHistoryResponseDto;
	BookingDetail bookDetail;
	long userId, flightId;
	int numberOfPassengerss;

	List<FlightDetailsResponseDto> response;

	@Before
	public void setup() {
		flightDetailsRequestDto = new FlightDetailsRequestDto();
		bookingDetailsHistoryResponseDto = new BookingDetailsHistoryResponseDto();
		listOfFlightDetailsResponseDto = new ArrayList<>();
		listOfChargeDetail = new ArrayList<>();
		bookDetail = new BookingDetail();
		listOfBookingDetail = new ArrayList<>();
		flightDetailsRequestDto.setDate("2020-03-10");
		flightDetailsRequestDto.setDestination("CHENNAI");
		flightDetailsRequestDto.setSource("BANGALORE");

		flightDetail = new FlightDetail();
		flightDetail.setAvailability("YES");
		flightDetail.setDay("MON");
		flightDetail.setDestination("CHENNAI");
		flightDetail.setFlightCode("AIRBLR2CH");
		flightDetail.setFlightId(1l);
		flightDetail.setFlightName("AIR INDIA");
		flightDetail.setFromTime("15:00:00");
		flightDetail.setSeatsAvailable(10);
		flightDetail.setSource("Bangalore");
		flightDetail.setToTime("17:00:00");

		listOflightDetail = new ArrayList<>();
		listOflightDetail.add(flightDetail);
		chargeDetail = new ChargeDetail();
		chargeDetail.setCharges(10.0);
		chargeDetail.setFlightCode("AIRBLR2CH");
		chargeDetail.setType("ECONOMY");
		listOfChargeDetail.add(chargeDetail);
		userId = 1L;

		bookDetail.setBookingId(1L);
		bookDetail.setDate("2020-04-02");
		bookDetail.setFlightCode("AIRBLR2CH");
		bookDetail.setNumberOfPassengers(2);
		bookDetail.setUserId(1L);
		flightId = 1l;

		listOfBookingDetail = new ArrayList<>();
		listOfBookingDetail.add(bookDetail);
		userDetail = new UserDetail();
		userDetail.setAge(20);
		userDetail.setEmailId("suprita@hcl.com");

		userDetail.setMobileNumber("0192837465");
		userDetail.setPassword("123");
		userDetail.setSex("FEMALE");
		userDetail.setUserId(1L);
		userDetail.setUserName("suprita");
		numberOfPassengerss = 1;

		passengerDetailsRequestDto = new ArrayList<>();
		response = new ArrayList<>();

	}

	@Test
	public void searchFlightDetails() throws CustomException, ParseException {
		Mockito.when(flightDetailRepository.findBySourceAndDestinationAndDay(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(Optional.of(listOflightDetail));
		Mockito.when(chargeDetailRepository.findByFlightCode(Mockito.anyString()))
				.thenReturn(Optional.of(listOfChargeDetail));

		response = flightServiceImpl.searchFlightDetails(flightDetailsRequestDto);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.size(), listOflightDetail.size());
		Assert.assertEquals(response.get(0).getDestination(), listOflightDetail.get(0).getDestination());

	}

	@Test(expected = CustomException.class)
	public void searchFlightDetailsFlightNotAvailable() throws CustomException, ParseException {

		Mockito.when(flightDetailRepository.findBySourceAndDestinationAndDay(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(Optional.empty());

		flightServiceImpl.searchFlightDetails(flightDetailsRequestDto);

	}

	@Test(expected = CustomException.class)
	public void searchFlightDetailsChargeDetailsEmpty() throws CustomException, ParseException {
		Mockito.when(flightDetailRepository.findBySourceAndDestinationAndDay(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(Optional.of(listOflightDetail));
		Mockito.when(chargeDetailRepository.findByFlightCode(Mockito.anyString())).thenReturn(Optional.empty());

		flightServiceImpl.searchFlightDetails(flightDetailsRequestDto);

	}

	@Test
	public void getBookingHistory() throws CustomException {
		Mockito.when(bookingDetailRepository.findByUserId(Mockito.anyLong()))
				.thenReturn(Optional.of(listOfBookingDetail));
		List<BookingDetailsHistoryResponseDto> response = flightServiceImpl.getBookingHistory(userId);
		Assert.assertNotNull(response);
		Assert.assertEquals(response.size(), listOfBookingDetail.size());
		// Assert.assertEquals(response.get(0).getUserId(),
		// listOfBookingDetail.get(0).getUserId());
	}

	@Test(expected = CustomException.class)
	public void getBookingHistoryNotAvailable() throws CustomException, ParseException {
		Mockito.when(bookingDetailRepository.findByUserId(Mockito.anyLong())).thenReturn(Optional.empty());

		flightServiceImpl.getBookingHistory(userId);
	}

	@Test
	public void bookingRequest() throws CustomException {

		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(userDetail));
		Mockito.when(chargeDetailRepository.findByFlightCode(Mockito.anyString()))
				.thenReturn(Optional.of(listOfChargeDetail));
		Mockito.when(flightDetailRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(flightDetail));

		String response = flightServiceImpl.bookingRequest(flightId, userId, numberOfPassengerss);
		Assert.assertNotNull(response);
		Assert.assertEquals("Enter Passenger Details", response);

	}

	@Test(expected = CustomException.class)
	public void bookingRequestNegativeUser() throws CustomException {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		flightServiceImpl.bookingRequest(flightId, userId, numberOfPassengerss);

	}

	@Test(expected = CustomException.class)
	public void bookingRequestNegativeFlight() throws CustomException {
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(userDetail));
		Mockito.when(flightDetailRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		flightServiceImpl.bookingRequest(flightId, userId, numberOfPassengerss);

	}

	@Test
	public void passengerDetails() throws CustomException {

		PassengerDetail passengerDetail = new PassengerDetail();

		BookingDetail bookingDetail1 = new BookingDetail();
		Mockito.when(bookingDetailRepository.save(Mockito.any(BookingDetail.class))).thenReturn(bookingDetail1);
		Mockito.when(passengerDetailRepository.save(Mockito.any(PassengerDetail.class))).thenReturn(passengerDetail);

		String response = flightServiceImpl.passengerDetails(passengerDetailsRequestDto);
		Assert.assertNotNull(response);
		Assert.assertEquals( "Booked Successfully",response);
	}

}
