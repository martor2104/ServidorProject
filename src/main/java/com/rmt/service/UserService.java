package com.rmt.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.rmt.dto.respuesta.UsuarioResponse;


public interface UserService {

    UserDetailsService userDetailsService();
    List<UsuarioResponse> getAllUsers();
}
