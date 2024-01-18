package com.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.dto.TodoDto;
import com.todo.dto.UserDto;

public interface UserRepo extends JpaRepository<UserDto, Integer>  {

}
