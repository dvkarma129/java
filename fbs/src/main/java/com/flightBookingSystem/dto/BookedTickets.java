package com.flightBookingSystem.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Booked_Tickets")
@Getter
@Setter
@ToString(exclude = {"flight", "user"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BookedTickets {

	@Id
	@GeneratedValue
	private int ticketId;

	private String name;

	private String contact;

	private String gender;

	private int seatsBooked;
	
	private int seatsCost;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flightId")
	private FlightlistDto flight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private UsersDto user;
	
	
}