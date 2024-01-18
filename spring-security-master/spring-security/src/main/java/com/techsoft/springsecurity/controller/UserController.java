package com.techsoft.springsecurity.controller;

import com.techsoft.springsecurity.entity.AuthRequest;
import com.techsoft.springsecurity.entity.Tweet;
import com.techsoft.springsecurity.entity.UserInfo;
import com.techsoft.springsecurity.repository.TweetRepository;
import com.techsoft.springsecurity.service.JwtService;
import com.techsoft.springsecurity.service.TwittService;
import com.techsoft.springsecurity.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private TwittService twittService;

	private int id = 0;

	@PostMapping("/signUp")
	public String signUp(@RequestBody UserInfo userInfo) {
		return userInfoService.signUp(userInfo);
	}

	@PostMapping("/login")
	public String addUser(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			this.id = userInfoService.getMyId(authRequest.getUserName());
			return jwtService.generateToken(authRequest.getUserName());
		} else {
			throw new UsernameNotFoundException("Invalid user request");
		}
	}

	@GetMapping("/getUsers")
	@PreAuthorize("hasAuthority('ADMIN_ROLES')")
	public List<UserInfo> getAllUsers() {
		return userInfoService.getAllUser();
	}

	@GetMapping("findUser/{name}")
	@PreAuthorize("hasAuthority('USER_ROLES')or hasAuthority('ADMIN_ROLES')")
	public UserInfo findUser(@PathVariable String name) {
		return userInfoService.findUser(name);
	}

	@GetMapping("/profile")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public UserInfo getMyProfile() {
		return userInfoService.getUser(this.id);
	}

	@PostMapping("/updateProfile")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public UserInfo updateProfile(@RequestBody UserInfo model) {
		return userInfoService.updateProfile(this.id, model);
	}

	@PostMapping("/deleteProfile")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public String deleteProfile() {
		userInfoService.deleteProfile(this.id);
		return "Profile deleted successfully";
	}

	@PostMapping("/removeUser/{userId}")
	@PreAuthorize("hasAuthority('ADMIN_ROLES')")
	public String removeUser(@PathVariable int userId) {
		userInfoService.removeUser(userId);
		return "User removed successfully";
	}

	// ==========================================================================================================

	@GetMapping("/allTwitts")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public List<Tweet> getAllTweets() {
		return twittService.getAllTweets();
	}

	@GetMapping("/myTwitts")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public List<Tweet> getMyTwitts() {
		return twittService.getMyTwitts(this.id);
	}

	@PostMapping("/newTwitts")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public Tweet newTwitts(@RequestBody Tweet model) {
		return twittService.newTweet(model, this.id);
	}

	@PostMapping("/updateTwitts/{tid}")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public Tweet updateTwitt(@RequestBody Tweet model, @PathVariable Long tid) {
		return twittService.updateTwitt(model, tid);
	}

	@PostMapping("/deleteTwitts/{tid}")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public String deleteTwitt(@PathVariable Long tid) {
		twittService.deleteTwitt(tid);
		return "twitt deleted successfully";
	}

	@PostMapping("/removeTwitts/{tid}")
	@PreAuthorize("hasAuthority('ADMIN_ROLES')")
	public String removeTwitts(@PathVariable Long tid) {
		twittService.removeTwitts(tid);
		return "removed twitt successfully";
	}

	@GetMapping("/hash/{hash}")
	@PreAuthorize("hasAuthority('USER_ROLES') or hasAuthority('ADMIN_ROLES')")
	public List<Tweet> getTwittsByHashtags(@PathVariable String hash) {
		return twittService.getTwittsByHashtags(hash);
	}

}
