package com.flightBookingSystem.service;

import com.flightBookingSystem.dto.UsersDto;

public interface EmailService {

	String sendMail(UsersDto udto);

	int sendOtp(UsersDto udto, int userId);

	void sendTicketEmail(UsersDto udto, byte[] pdfData);
	
	

}
