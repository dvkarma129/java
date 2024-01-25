package com.springsec.restcontroller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

import org.slf4j.Logger;

record Todo(String username, String description){}

@RestController
public class TodoResource {

	Logger logger =  LoggerFactory.getLogger(getClass()); 
	private static final List<Todo> TODO_LIST = List.of(new Todo("jhon", "learn exception handling"),
			new Todo("rin", "learn restapis"));

	@GetMapping("/todos")
	public List<Todo> getAllTodos(){
		return TODO_LIST;
	}
	
	@GetMapping("/users/{id}/todos")
//	@PreAuthorize("hasRole('USER') and #username == authentication.name")//recomded
//	@PostAuthorize("returnObject.username =='rin'")//recommnded
	@RolesAllowed({"ADMIN", "USER"})
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public Todo getUserTodos(@PathVariable int id) {
		return TODO_LIST.get(id-1);
	}
	
	@PostMapping("/users/{username}/todos")
	public void addTodo(@PathVariable String username, @RequestBody Todo todo) {
		logger.info("Create {} for {}",todo,username);
	}
}

