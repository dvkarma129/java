package com.example.demo1.dto;


import jakarta.validation.constraints.*; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data
public class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Pattern(regexp = "[^0-9]*",  message = "Numbers not allowed") 
	@NotBlank(message = "Name cannot be BLANK")
	private String name;
	
	@Email(message = "@ compulsory")
	@NotBlank(message = "email cannot be BLANK")
	private String email;
	
	private String tech;
}
