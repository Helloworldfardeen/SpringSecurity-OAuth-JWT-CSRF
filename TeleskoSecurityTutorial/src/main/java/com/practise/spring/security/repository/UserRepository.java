package com.practise.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practise.spring.security.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);

}
