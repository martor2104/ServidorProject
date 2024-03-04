package com.rmt.security.user;

import com.rmt.security.user.request.SignUpRequest;
import com.rmt.security.user.request.SigninRequest;
import com.rmt.security.user.response.JwtAuthenticationResponse;

public interface AuthenticationService {
	
	/** REGISTRO */
	JwtAuthenticationResponse signup(SignUpRequest request);
    /** ACCESO a Token JWT */
	JwtAuthenticationResponse signin(SigninRequest request);
}
