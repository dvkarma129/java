package com.flightBookingSystem.dto;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Airlines")
@Getter
@Setter
@ToString(exclude = "flights")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AirlineDto {

	@Id
	@GeneratedValue
	private int airlineId;
	
	private String airlineName;
	
	@OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightlistDto> flights;
	
	private String airlineImgPath;
	
	@Transient
	MultipartFile airlineImg;
}



