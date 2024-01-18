package com.mvcdemo.mvc2.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Emp {

	@Id
	private long id;
	
	@Size(max = 1 , message = "field empty")
	private String firstName;
	
	private String lastName;
	
	@Column(length = 12)
	private Long contact;
	
	private String role;
	
	private String lang;
	
	private String gender;
	
	private String address;
	
	private Long salary;
}
