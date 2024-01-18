package com.todo.jwt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtResource {
	//
	@PostMapping("/authenticate")
	public JwtResponse authenticateAndCreateJwtToken(
						@RequestBody JwtRequest request) {
		return new JwtResponse("JWT Token");
	}

}

record JwtRequest(String username, String password) {}
record JwtResponse(String token) {}