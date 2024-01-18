package com.flightBookingSystem.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Flights")
@Getter
@Setter
@ToString(exclude = {"airline", "departureAirport", "arrivalAirport"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class FlightlistDto {

	@Id
	@GeneratedValue
	@Column(name = "flightId")
	private int flightId;

	private int planeNo;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime departureDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime arrivalDate;

	private int seats;

	private int seatsBooked;

	private int seatsAvailable;

	private int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airlineId")
	private AirlineDto airline;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departureAirportId")
	private AirportDto departureAirport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "arrivalAirportId")
	private AirportDto arrivalAirport;

}
