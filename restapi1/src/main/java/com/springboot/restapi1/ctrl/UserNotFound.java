package com.springboot.restapi1.ctrl;

import java.time.LocalDate;

import com.springboot.restapi1.dto.User;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserNotFound extends Exception{

	private LocalDate time;
	private String mess;
	private String details;
	public UserNotFound(LocalDate time, String mess, String details) {
		super();
		this.time = time;
		this.mess = mess;
		this.details = details;
	}
	
	
	
}
