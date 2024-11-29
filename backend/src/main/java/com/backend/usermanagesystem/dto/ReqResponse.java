package com.backend.usermanagesystem.dto;

import java.util.List;

import com.backend.usermanagesystem.entity.Users;

import lombok.Getter;
import lombok.Setter;


public class ReqResponse {

	@Getter
	@Setter
	private int statusCode;
	
	@Getter
	@Setter
    private String error;
	
	@Getter
	@Setter
    private String message;
	
	@Getter
	@Setter
    private String token;
	
	@Getter
	@Setter
    private String refreshToken;
	
	@Getter
	@Setter
    private String expirationTime;
	
	@Getter
	@Setter
    private String username;
	
	@Getter
	@Setter
    private String city;
	
	@Getter
	@Setter
    private String role;
	
	@Getter
	@Setter
    private String email;
	
	@Getter
	@Setter
    private String password;
	
	@Getter
	@Setter
    private Users users;
	
	@Getter
	@Setter
    private List<Users> userList;
}
