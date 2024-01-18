package com.flightBookingSystem.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Admin_Dto")
@Getter
@Setter
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AdminDto {

	@Id
	@GeneratedValue
	private int adminId;
	
	@Column(name="Admin_Username")
	private String adminUsername;
	
	@Column(name="Admin_Gmail")
	private String adminGmail;
	
	@Column(name="Admin_Password")
	private String adminPassword;
	
	@Column(name ="Revenue")
	private int revenue;
	
	@Column(name="totalUsers")
	private int totalUsers;
	
}
