package com.org.flightbooking.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.flightbooking.dto.BookingDetailsHistoryResponseDto;
import com.org.flightbooking.dto.ChargeDetailsResponseDto;
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
import com.org.flightbooking.utility.FlightUtility;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightDetailRepository flightDetailRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PassengerDetailRepository passengerDetailRepository;

	@Autowired
	BookingDetailRepository bookingDetailRepository;

	@Autowired
	ChargeDetailRepository chargeDetailRepository;

	BookingDetail bookingDetail;
	List<PassengerDetailsRequestDto> passengerDetailsRequestDto;

	String flightCode;
	int numberOfPassengerss1;
	long flightId1;
	long userId1;
	String dateForBooking;
	double charges;

	long bookingRequestUserId;

	private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

	/**
	 * @author Suprita
	 * @since 2020-03-12 This method gives us List of Flights available for
	 *        requested date, requested source and destination
	 * 
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	public List<FlightDetailsResponseDto> searchFlightDetails(FlightDetailsRequestDto flightDetailsRequestDto)
			throws CustomException, ParseException {

		logger.info("searchFlightDetails method of FlightServiceImpl class");

		dateForBooking = flightDetailsRequestDto.getDate();

		Date requestedDate = null;
		requestedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateForBooking);
		String weekOfDate = new SimpleDateFormat("EE").format(requestedDate);
		Date today;
		today = Calendar.getInstance().getTime();

		if (requestedDate.before(today)) {
			throw new CustomException(FlightUtility.DAY_BEFORE_TODAY);
		}
		flightCode = null;
		Optional<List<FlightDetail>> availableFlightList = flightDetailRepository.findBySourceAndDestinationAndDay(
				flightDetailsRequestDto.getSource(), flightDetailsRequestDto.getDestination(), weekOfDate);
		if (!availableFlightList.isPresent()) {
			throw new CustomException(FlightUtility.FLIGHTS_NOT_AVAILABLE);
		}

		List<FlightDetailsResponseDto> listFlightDetailsResponseDto = new ArrayList<>();
		FlightDetailsResponseDto flightDetailsResponseDto = null;

		for (FlightDetail flightDetail : availableFlightList.get()) {
			flightCode = flightDetail.getFlightCode();
			Optional<List<ChargeDetail>> optionalChargeDetails = chargeDetailRepository
					.findByFlightCode(flightDetail.getFlightCode());

			if (!optionalChargeDetails.isPresent()) {
				throw new CustomException("List empty");
			}
			List<ChargeDetailsResponseDto> listChargeDetailsResponseDto = new ArrayList<>();
			flightDetailsResponseDto = new FlightDetailsResponseDto();

			ChargeDetailsResponseDto chargeDetailsResponseDto = null;
			for (ChargeDetail chargeDetail : optionalChargeDetails.get()) {
				if (flightDetail.getAvailability().equalsIgnoreCase("YES")
						&& chargeDetail.getFlightCode().equalsIgnoreCase(flightDetail.getFlightCode())) {
					chargeDetailsResponseDto = new ChargeDetailsResponseDto();
					BeanUtils.copyProperties(chargeDetail, chargeDetailsResponseDto);
					BeanUtils.copyProperties(flightDetail, flightDetailsResponseDto);
					listChargeDetailsResponseDto.add(chargeDetailsResponseDto);
				}

			}
			flightDetailsResponseDto.setChargeDetailsResponseDto(listChargeDetailsResponseDto);
			listFlightDetailsResponseDto.add(flightDetailsResponseDto);

		}
		return listFlightDetailsResponseDto;
	}

	/**
	 * @author Koushik
	 * @since 2020-03-12 based on userId, fetching the flight booking history
	 */

	@Override
	public List<BookingDetailsHistoryResponseDto> getBookingHistory(long userId) throws CustomException {
		Optional<List<BookingDetail>> listbookingDetail = bookingDetailRepository.findByUserId(userId);

		if (!listbookingDetail.isPresent()) {
			throw new CustomException(FlightUtility.LIST_EMPTY);
		}

		List<BookingDetailsHistoryResponseDto> listBookingDetailsHistoryResponseDto = new ArrayList<>();
		for (BookingDetail bookingDetail1 : listbookingDetail.get()) {
			BookingDetailsHistoryResponseDto bookingDetailsHistoryResponseDto = new BookingDetailsHistoryResponseDto();
			BeanUtils.copyProperties(bookingDetail1, bookingDetailsHistoryResponseDto);
			listBookingDetailsHistoryResponseDto.add(bookingDetailsHistoryResponseDto);
		}

		return listBookingDetailsHistoryResponseDto;

	}

	/**
	 * @author Suprita
	 * @since 2020-03-12 User will send flightId and Number of passengers to book a
	 *        flight
	 */
	@SuppressWarnings("unused")
	@Override
	public String bookingRequest(long flightId, long userId, int numberOfPassengerss) throws CustomException {
		numberOfPassengerss1 = numberOfPassengerss;

		bookingRequestUserId = userId;

		flightId1 = flightId;
		Optional<UserDetail> userOptional = userRepository.findById(userId);
		Optional<FlightDetail> optionalFlightDetails = flightDetailRepository.findById(flightId);

		if (!userOptional.isPresent()) {
			throw new CustomException("User does not exists");
		}
		if (!optionalFlightDetails.isPresent()) {
			throw new CustomException("Flight with this ID does not exists");

		}

		flightCode = optionalFlightDetails.get().getFlightCode();
		Optional<List<ChargeDetail>> listOfOptionalCharge = chargeDetailRepository.findByFlightCode(flightCode);
		if (listOfOptionalCharge.isPresent()) {
			charges = listOfOptionalCharge.get().get(0).getCharges();
		}

		return "Enter Passenger Details";
	}

	/**
	 * @author Kusuma :Saving passenger details and Flight booking details
	 * @since 2020-03-12
	 * 
	 */

	@Override
	public String passengerDetails(List<PassengerDetailsRequestDto> listOfPassengerDetailsRequestDto)
			throws CustomException {

		if (listOfPassengerDetailsRequestDto.size() == numberOfPassengerss1) {
			bookingDetail = new BookingDetail();
			bookingDetail.setDate(dateForBooking);
			bookingDetail.setFlightCode(flightCode);
			bookingDetail.setNumberOfPassengers(numberOfPassengerss1);
			bookingDetail.setUserId(bookingRequestUserId);

			bookingDetailRepository.save(bookingDetail);
			for (PassengerDetailsRequestDto passengerDetailsRequestDto1 : listOfPassengerDetailsRequestDto) {
				PassengerDetail passengerDetail = new PassengerDetail();
				BeanUtils.copyProperties(passengerDetailsRequestDto1, passengerDetail);

				passengerDetail.setBookingId(bookingDetail.getBookingId());

				passengerDetailRepository.save(passengerDetail);
			}
		} else if (listOfPassengerDetailsRequestDto.size() < numberOfPassengerss1) {
			throw new CustomException("Please enter " + numberOfPassengerss1 + " passenger details");
		} else {
			throw new CustomException("You are entering more Passenger details,Please enter " + numberOfPassengerss1
					+ " passenger details");
		}
		return "Booked Successfully";
	}

}
