package com.practise.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practise.spring.security.model.User;
import com.practise.spring.security.service.UserService;

@RestController
@RequestMapping("/public")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<User> loadUserInDb(@RequestBody User u)
	{
		try {
			User user = userService.saveUser(u);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	@PostMapping("/login")
	public String loginUser(@RequestBody User user)
	{
		return userService.verify(user);
	}
	

	
}
