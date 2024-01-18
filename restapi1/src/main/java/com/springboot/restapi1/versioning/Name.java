package com.springboot.restapi1.versioning;

import lombok.Data;

@Data
public class Name {

	private String fName;
	private String lName;
	public Name(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
	}
}
