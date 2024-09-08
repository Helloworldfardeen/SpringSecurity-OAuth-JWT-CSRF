package com.practise.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecuriyConfig {

	@Autowired
	private JwtFilter jwtfilter;
	// Most Important Code for Configuration perspective
	@Autowired
	private UserDetailsService userDetailsService;// this interface understand
	// here you can bypass and ignoring security layer....

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// Disable CSRF protection (useful for stateless APIs)
		http.csrf(customizer -> customizer.disable());
//		http.csrf(Customizer.withDefaults())

		// Require authentication for all requests
		http.authorizeHttpRequests(
				request -> request.requestMatchers("/public/login","/public/register").permitAll().anyRequest().authenticated());

		// Enable form login for browser-based authentication
		http.formLogin(Customizer.withDefaults());

		// Enable basic authentication for tools like Postman
		http.httpBasic(Customizer.withDefaults());

		// Set the session management policy to stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
	    http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		// Return the configured SecurityFilterChain object
		return http.build();
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails user1 =
	 * User.withDefaultPasswordEncoder() .username("aman") .password("a@123")
	 * .roles("USER") .build(); UserDetails user2 =
	 * User.withDefaultPasswordEncoder() .username("fardeen") .password("f@123")
	 * .roles("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user1,user2);//this varargs }
	 */
	@Bean
	AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

	// for Jwt setup...
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();

	}

}
//from docs
/*
 * @Configuration
 * 
 * @EnableWebSecurity public class MyWebSecurityConfiguration {
 * 
 * @Bean public WebSecurityCustomizer webSecurityCustomizer() { return (web) ->
 * web.ignoring() // Spring Security should completely ignore URLs starting with
 * /resources/ .requestMatchers("/resources/**"); }
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * http.authorizeHttpRequests().requestMatchers("/public/**").permitAll().
 * anyRequest() .hasRole("USER").and() // Possibly more configuration ...
 * .formLogin() // enable form based log in // set permitAll for all URLs
 * associated with Form Login .permitAll(); return http.build(); }
 * 
 * @Bean public UserDetailsService userDetailsService() { UserDetails user =
 * User.withDefaultPasswordEncoder() .username("user") .password("password")
 * .roles("USER") .build(); UserDetails admin =
 * User.withDefaultPasswordEncoder() .username("admin") .password("password")
 * .roles("ADMIN", "USER") .build(); return new InMemoryUserDetailsManager(user,
 * admin); }
 */
