package com.springboot.restapi1.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.springboot.restapi1.dto.User;

@Component
public class UserDao {
	
	private static int count = 0;

	private static List<User> users = new ArrayList<>(); 
	
	static {
		users.add(new User(count++,"rick",LocalDate.now().minusYears(20)));
		users.add(new User(count++,"kick",LocalDate.now().minusYears(10)));
		users.add(new User(count++,"sick",LocalDate.now().minusYears(25)));
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super User> Predicate = user -> user.getId().equals(id);
		return users.stream().filter(Predicate).findFirst().get();
		
		
	}

	public User addUser(User user) {
		user.setId(count++);
		users.add(user);
		return user;
	}

	public String delUser(int id) {
		users.remove(id);
		return "Emp deleted";
	}
}
