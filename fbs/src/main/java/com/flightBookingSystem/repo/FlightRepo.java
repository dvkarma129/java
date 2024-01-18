package com.flightBookingSystem.repo;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.flightBookingSystem.dto.AdminDto;
import com.flightBookingSystem.dto.AirlineDto;
import com.flightBookingSystem.dto.AirportDto;
import com.flightBookingSystem.dto.FlightlistDto;

@Repository
@EnableJpaRepositories
public interface FlightRepo extends JpaRepository<FlightlistDto, Integer> {

	
	List<FlightlistDto> findByDepartureAirport_airportNameOrArrivalAirport_airportNameOrDepartureDate(
			String departureAirport, String arrivalAirport, Date departureDate);

	List<FlightlistDto> findByDepartureAirport_AirportNameOrArrivalAirport_AirportNameOrDepartureDate(
			String airportName, String airportName2, Date departureDate);

	List<FlightlistDto> findByDepartureAirport_AirportNameAndArrivalAirport_AirportNameOrDepartureDate(
			String airportName, String airportName2, Date departureDate);

	List<FlightlistDto> findByDepartureAirport_AirportNameAndArrivalAirport_AirportNameAndDepartureDate(
			String airportName, String airportName2, Date departureDate);

	List<FlightlistDto> findByDepartureAirport_AirportNameAndArrivalAirport_AirportNameOrDepartureDate(
			String airportName, String airportName2, LocalDateTime departureDate);

}
