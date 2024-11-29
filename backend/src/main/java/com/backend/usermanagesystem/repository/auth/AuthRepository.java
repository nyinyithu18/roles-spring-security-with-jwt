package com.backend.usermanagesystem.repository.auth;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.backend.usermanagesystem.entity.Users;

@Mapper
public interface AuthRepository {

	Optional<Users> findByEmail(String email);
	public int accSave(Users user);
}
