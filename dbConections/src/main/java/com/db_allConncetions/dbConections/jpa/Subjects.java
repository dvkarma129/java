package com.db_allConncetions.dbConections.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subjects {

	@Id
	private int id;
	
	private String name;

	public Subjects(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
