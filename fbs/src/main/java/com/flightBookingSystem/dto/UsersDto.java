package com.flightBookingSystem.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UsersDto {

	@Id
	@GeneratedValue
	private int userId;

	private String userFullName;

	private String userName;
	
	@Column(unique = true)
	private String userContact;

	@Column(unique = true)
	private String userGmail;

	private String gender;

	private Date dob;

	private String userPassword;

	private int balance;
	
//	private int mySeats;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BookedTickets> bookedTickets;
	
	@Nullable
	@Column(name="USERS_OTP")
	private int userOtp;
}
