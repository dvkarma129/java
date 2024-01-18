package com.todo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.dto.TodoDto;

public interface TodoRepo extends JpaRepository<TodoDto, Integer> {

}
