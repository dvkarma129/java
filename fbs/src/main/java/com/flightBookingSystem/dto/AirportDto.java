package com.flightBookingSystem.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Airports")
@Getter
@Setter
@ToString(exclude = {"departureFlights", "arrivalFlights"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AirportDto {

	@Id
	@GeneratedValue
	private int airportId;
	
	@Column(name ="Airport_Name")
	private String airportName;
	
	@Column(name ="Airport_Location")
	private String airportLocation;
	
	@OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightlistDto> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightlistDto> arrivalFlights;
}