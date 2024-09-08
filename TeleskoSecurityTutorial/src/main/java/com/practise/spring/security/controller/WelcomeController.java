package com.practise.spring.security.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class WelcomeController {

	@GetMapping("/")
	public String welcomeMethod(HttpServletRequest request)
	{
		return "Welcome To The Project "+request.getSession().getId();
	}
	@GetMapping("csrf-token")
	public CsrfToken getCSRFToken(HttpServletRequest request)
	{
		return (CsrfToken) request.getAttribute("_csrf");
	}
}
