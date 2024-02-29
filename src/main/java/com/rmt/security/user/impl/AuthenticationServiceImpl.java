package com.rmt.security.user.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rmt.entities.Role;
import com.rmt.entities.Usuario;
import com.rmt.repository.UserRepository;
import com.rmt.security.user.AuthenticationService;
import com.rmt.security.user.JwtService;
import com.rmt.security.user.request.SignUpRequest;
import com.rmt.security.user.request.SigninRequest;
import com.rmt.security.user.response.JwtAuthenticationResponse;

import lombok.Builder;

@Builder
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = null;
    private final JwtService jwtService = null;
    private final AuthenticationManager authenticationManager = null;
    
    
	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        // Corrige la forma de construir el objeto 'User'
        Usuario user = new Usuario();
        user.setName(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        user.setTelefono(request.getTelefono());
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	
	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
	       // Maneja la autenticación
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
       // SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	
}
