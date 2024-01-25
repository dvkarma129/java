package com.empmngt.dto;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.empmngt.config.CustomDepartmentSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Employee")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EmpDto implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long empId;

	@NotBlank(message = "First name cannot be null")
	@Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
	private String firstName;

	@NotBlank(message = "Last name cannot be null")
	@Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
	private String lastName;

	@Email(message = "Email should be valid")
	@NotBlank(message = "Email cannot be blank")
	@Column(nullable = true, unique = true)
	private String email;
	
	private String password;

	@Size(max = 10, message = "Phone number is not valid")
	private String phoneNumber;

	@Past(message = "Date of birth must be in the past")
	private LocalDate dateOfBirth;

	@PositiveOrZero(message = "Salary must be positive or zero")
	private long salary;

	@NotBlank(message = "Position is required")
	private String position;

	@NotBlank(message = "Gender is required")
	private String gender;

	@NotBlank(message = "City is required")
	private String city;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id", referencedColumnName = "department_id")
	@JsonSerialize(using = CustomDepartmentSerializer.class)
	public DeptDto department;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}