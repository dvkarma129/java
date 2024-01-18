package com.springsec.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsec.dto.User;
public interface UserRepository extends JpaRepository<User, String> {
    // Define custom queries if needed
}
