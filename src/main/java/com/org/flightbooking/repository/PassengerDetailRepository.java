package com.org.flightbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.flightbooking.entity.PassengerDetail;

@Repository
public interface PassengerDetailRepository extends JpaRepository<PassengerDetail, Long> {
	
}
