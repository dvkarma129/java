package com.springsec.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class helloworld {

	@GetMapping("/hello")
	private String HelloWorld() {
		return "hello";
	}
}
