package com.techsoft.springsecurity.service;

import com.techsoft.springsecurity.entity.Tweet;
import com.techsoft.springsecurity.entity.UserInfo;
import com.techsoft.springsecurity.repository.TweetRepository;
import com.techsoft.springsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
		return userInfo.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
	}

	public String signUp(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "User added successfully\nLogin now";
	}
	
	public int getMyId(String userName) {
		UserInfo user =  userInfoRepository.findByName(userName).get();
	return user.getId();
	}
	
	//============================	USERS ==================================================

	public List<UserInfo> getAllUser() {
		return userInfoRepository.findAll();
	}

	public UserInfo findUser(String name) {
		return userInfoRepository.findByName(name).get();
	}

	public UserInfo getUser(Integer id) {
		return userInfoRepository.findById(id).get();
	}

	

	public UserInfo updateProfile(int id, UserInfo model) {
		UserInfo userInfo =  userInfoRepository.findById(id).get();
		userInfo.setName(model.getName());
		userInfo.setEmail(model.getEmail());
		return userInfoRepository.save(userInfo);
	}

	public void deleteProfile(int id) {
		userInfoRepository.deleteById(id);
		
	}

	public void removeUser(int userId) {
		userInfoRepository.deleteById(userId);
		
	}
}
