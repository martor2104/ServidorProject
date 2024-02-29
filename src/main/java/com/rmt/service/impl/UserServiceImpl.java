package com.rmt.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rmt.dto.respuesta.UsuarioResponse;
import com.rmt.repository.UserRepository;
import com.rmt.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetailsService userDetailsService() {
		 return new UserDetailsService() {
	            @Override
	            public UserDetails loadUserByUsername(String username) {
	                return repository.findByEmail(username)
	                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	            }
	        };
	}

	@Override
	public List<UsuarioResponse> getAllUsers() {
		/*List<UsuarioResponse> allUsers =  repository.findAll().stream()
			    .map(usuario -> new UsuarioResponse(usuario.getName(), usuario.getLastName(), usuario.getEmail(), usuario.getRoles().toString()))
			    .collect(Collectors.toList());
			    */
		 return null;
	}


}
