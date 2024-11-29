package com.backend.usermanagesystem.service;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTUtils {

	public String generateToken(UserDetails userDetails);
	public String generateRefreshToken(HashMap<String, Object> claim, UserDetails userDetails);
	public String extractUsername(String token);
	public boolean isValidToken(String token, UserDetails userDetails);
	public boolean isTokenExpired(String token);
}
