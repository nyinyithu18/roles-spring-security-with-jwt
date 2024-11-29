package com.backend.usermanagesystem.service;

import com.backend.usermanagesystem.dto.ReqResponse;

public interface AuthService {

	public ReqResponse login(ReqResponse reqRes);
	public ReqResponse register(ReqResponse reqRes);
}
