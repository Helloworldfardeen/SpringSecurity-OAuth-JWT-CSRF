package com.practise.spring.security.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	private String SecretKey = "";

	public JWTService() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
			javax.crypto.SecretKey sk = keyGenerator.generateKey();
			SecretKey = Base64.getEncoder().encodeToString(sk.getEncoded());

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException();
		}
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30)).and().signWith(getKey()).compact();
	}

	private javax.crypto.SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SecretKey);

		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

	private boolean isTokenExpired(String token) {

		return extractExiration(token).before(new Date());
	}

	private Date extractExiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

}
