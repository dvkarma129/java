package com.mvcdemo.mvc2.service;

import org.springframework.stereotype.Service;

import com.mvcdemo.mvc2.dto.Emp;
import com.mvcdemo.mvc2.repo.EmpRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class serv {
	
	private final EmpRepo empRepo;
	
}
