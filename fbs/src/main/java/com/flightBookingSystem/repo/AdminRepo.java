package com.flightBookingSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.flightBookingSystem.dto.AdminDto;

@Repository
@EnableJpaRepositories
public interface AdminRepo extends JpaRepository<AdminDto, Integer> {

	int countByAdminGmailAndAdminPassword(String adminGmail, String adminPassword);

	AdminDto getByAdminGmailAndAdminPassword(String adminGmail, String adminPassword);

}
