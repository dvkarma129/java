package com.flightBookingSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.flightBookingSystem.dto.AdminDto;
import com.flightBookingSystem.dto.AirlineDto;
import com.flightBookingSystem.dto.AirportDto;

@Repository
@EnableJpaRepositories
public interface AirportRepo extends JpaRepository<AirportDto, Integer> {

	AirportDto findByAirportName(String airportName);

}
