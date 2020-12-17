package com.org.flightbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.flightbooking.entity.ChargeDetail;

@Repository
public interface ChargeDetailRepository extends JpaRepository<ChargeDetail, Long> {

	Optional<List<ChargeDetail>> findByFlightCode(String flightCode);
	
}
