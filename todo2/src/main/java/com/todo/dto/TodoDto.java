package com.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TodoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int todoId;

    private String todo;

    @ManyToOne
    @JoinColumn(name = "UserDto_userId")
    @JsonIgnore
    private UserDto user;
}