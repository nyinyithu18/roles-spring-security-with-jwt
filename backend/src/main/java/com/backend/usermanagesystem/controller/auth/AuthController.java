package com.backend.usermanagesystem.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.usermanagesystem.dto.ReqResponse;
import com.backend.usermanagesystem.service.serviceimpl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ReqResponse> login(@RequestBody ReqResponse reqRes){
		return ResponseEntity.ok(authServiceImpl.login(reqRes));
	}
	
	@PostMapping("/register")
	public ResponseEntity<ReqResponse> register(@RequestBody ReqResponse reqRes){
		return ResponseEntity.ok(authServiceImpl.register(reqRes));
	}
}
