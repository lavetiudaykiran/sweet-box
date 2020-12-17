package com.org.flightbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.flightbooking.entity.UserDetail;

@Repository
public interface UserRepository extends JpaRepository<UserDetail, Long> {
	Optional<UserDetail> findByEmailId(String emailId);

}
