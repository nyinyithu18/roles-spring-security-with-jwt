package com.backend.usermanagesystem.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.usermanagesystem.repository.auth.AuthRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private AuthRepository authRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = authRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email >> " + email));
		System.out.println("User Found >> " + user);
		return new User(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"	+ user.getRole()))); 
	}

}
