package com.backend.usermanagesystem.service.serviceimpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.usermanagesystem.dto.ReqResponse;
import com.backend.usermanagesystem.entity.Users;
import com.backend.usermanagesystem.repository.auth.AuthRepository;
import com.backend.usermanagesystem.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private JWTUtilsImpl jwtUtilsImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ReqResponse login(ReqResponse reqRes) {
		ReqResponse response = new ReqResponse();
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqRes.getEmail(),reqRes.getPassword()));
			
			var user = authRepository.findByEmail(reqRes.getEmail()).orElseThrow(() -> new RuntimeException("User Not Found!"));
			String token = jwtUtilsImpl.generateToken(user);
			String refreshToken = jwtUtilsImpl.generateRefreshToken(new HashMap<>(), user);
			
			response.setStatusCode(200);
			response.setToken(token);
			response.setRole(user.getRole());
			response.setRefreshToken(refreshToken);
			response.setExpirationTime("24Hrs");
			response.setMessage("Successfully Logged In");
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public ReqResponse register(ReqResponse reqRes) {
		ReqResponse res = new ReqResponse();
		
		try {
			Users user = new Users();
			user.setUsername(reqRes.getUsername());
			user.setEmail(reqRes.getEmail());
			user.setPassword(passwordEncoder.encode(reqRes.getPassword()));
			user.setCity(reqRes.getCity());
			if(reqRes.getRole() == "ADMIN") {
				user.setRole_id(2);
			}else if(reqRes.getRole() == "USER") {
				user.setRole_id(1);
			}	
			int resUser = authRepository.accSave(user);
			if(resUser > 0) {
				res.setStatusCode(200);
				res.setMessage("Account create successfully!");
				res.setUsers(user);
			}
		} catch (Exception e) {
			res.setStatusCode(500);
			res.setMessage(e.getMessage());
		}
		return res;
	}

}
