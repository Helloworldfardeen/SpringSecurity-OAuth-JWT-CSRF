package com.practise.local.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	//https://console.cloud.google.com/apis/credentials?project=proud-research-435017-s6
    //Authorised redirect URIs
    //http://localhost:8080/login/oauth2/code/google
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests(auth->auth.anyRequest().authenticated())
		.oauth2Login(Customizer.withDefaults());
		return http.build();
	}
}
