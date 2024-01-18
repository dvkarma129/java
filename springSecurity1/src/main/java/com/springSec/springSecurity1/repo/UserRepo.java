package com.springSec.springSecurity1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springSec.springSecurity1.dto.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
