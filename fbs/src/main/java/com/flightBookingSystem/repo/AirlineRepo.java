package com.flightBookingSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.flightBookingSystem.dto.AdminDto;
import com.flightBookingSystem.dto.AirlineDto;

@Repository
@EnableJpaRepositories
public interface AirlineRepo extends JpaRepository<AirlineDto, Integer> {

	AirlineDto findByAirlineName(String airlineName);


}
