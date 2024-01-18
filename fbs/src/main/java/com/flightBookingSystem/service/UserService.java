package com.flightBookingSystem.service;

import org.springframework.stereotype.Service;

import com.flightBookingSystem.dto.UsersDto;
import com.flightBookingSystem.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepo userRepo;
	private final EmailService emailService;

	public boolean userLogin(UsersDto model) {
		int count = userRepo.countByUserGmailAndUserPassword(model.getUserGmail(),model.getUserPassword());
		if (count>0) {
			return true;
		}
		return false;
	}

	public UsersDto getUserData(UsersDto model) {
		UsersDto udto = userRepo.getByUserGmailAndUserPassword(model.getUserGmail(),model.getUserPassword());
		return udto;
	}

	public void registerUser(UsersDto model) {
		UsersDto udto = new UsersDto();
		udto.setUserName(model.getUserName());
		udto.setUserFullName(model.getUserFullName());
		udto.setUserContact(model.getUserContact());
		udto.setGender(model.getGender());
		udto.setDob(model.getDob());
		udto.setUserGmail(model.getUserGmail());
		udto.setUserPassword(model.getUserPassword());
		userRepo.save(udto);
		
		emailService.sendMail(udto);
	}

	public void updateUser(UsersDto model, int userId) {
		// TODO Auto-generated method stub
		UsersDto udto = userRepo.getById(userId);
		udto.setUserName(model.getUserName());
		udto.setUserFullName(model.getUserFullName());
		udto.setUserContact(model.getUserContact());
		udto.setGender(model.getGender());
		udto.setDob(model.getDob());
		userRepo.save(udto);
		
	}
	
	
}
