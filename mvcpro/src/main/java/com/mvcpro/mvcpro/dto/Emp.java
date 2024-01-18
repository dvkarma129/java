package com.mvcpro.mvcpro.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Emp {

	@Id
	private long id;
	
	
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
