package com.practise.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practise.spring.security.model.User;
import com.practise.spring.security.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JWTService jwtService;
	@Autowired
	AuthenticationManager authManager;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	public User saveUser(User user) {
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);// this only will Do your Work.....
		return userRepo.save(user);
	}
//how you are going to verify...

	public String verify(User user) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			return  jwtService.generateToken(user.getUsername());

		}
		return "failed";
	}
}
