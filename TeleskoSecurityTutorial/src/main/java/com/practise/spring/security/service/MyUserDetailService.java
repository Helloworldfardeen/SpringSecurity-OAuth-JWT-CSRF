package com.practise.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practise.spring.security.model.User;
import com.practise.spring.security.model.UserPrincipal;
import com.practise.spring.security.repository.UserRepository;
@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    
		User user = userRepo.findByUsername(username);
		if(user == null)
		{
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return new UserPrincipal(user);
	}
	
	
	

}
