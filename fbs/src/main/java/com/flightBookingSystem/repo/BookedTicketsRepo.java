package com.flightBookingSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightBookingSystem.dto.BookedTickets;
import com.flightBookingSystem.dto.UsersDto;

@Repository
public interface BookedTicketsRepo extends JpaRepository<BookedTickets, Integer> {

	List<BookedTickets> getByUser(UsersDto udto);

	
}
