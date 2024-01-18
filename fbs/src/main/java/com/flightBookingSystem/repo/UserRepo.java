package com.flightBookingSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightBookingSystem.dto.UsersDto;

@Repository
public interface UserRepo extends JpaRepository<UsersDto, Integer> {

	int countByUserGmailAndUserPassword(String userGmail, String userPassword);

	UsersDto getByUserGmailAndUserPassword(String userGmail, String userPassword);

}
