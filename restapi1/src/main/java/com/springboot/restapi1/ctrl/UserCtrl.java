package com.springboot.restapi1.ctrl;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.restapi1.dao.UserDao;
import com.springboot.restapi1.dto.User;

import lombok.RequiredArgsConstructor;

@RestController

@RequestMapping("/user")
@RequiredArgsConstructor
public class UserCtrl {
	
	@Autowired
	private MessageSource messageSource;
	
	public UserCtrl(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userDao.getUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable int id) throws UserNotFound{
		return userDao.getUserById(id); 
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User savedUser = userDao.addUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public String delUser(@PathVariable int id) throws UserNotFound{ 
		return userDao.delUser(id); 
	}

	@GetMapping(path = "/inter")
	public String helloWorldInternationalized() throws NullPointerException{
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
	}
	
}
