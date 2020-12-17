package com.org.flightbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.flightbooking.entity.FlightDetail;

@Repository
public interface FlightDetailRepository extends JpaRepository<FlightDetail, Long> {
	Optional<List<FlightDetail>> findBySourceAndDestinationAndDay(String source, String destination, String day);
}
