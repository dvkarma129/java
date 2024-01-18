package com.db_allConncetions.dbConections.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NewEmp {

	@Id
	private int id;
	
	private String name;
	
	private String email;

	public NewEmp(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "NewEmp [id=" + id + ", name=" + name + ", email=" + email + "]";
	}



	public NewEmp() {
		super();
	}


	
	
}
