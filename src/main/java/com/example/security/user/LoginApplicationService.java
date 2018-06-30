package com.example.security.user;

import com.example.security.jwt.JwtTokenService;
import com.example.security.jwt.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginApplicationService {
    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    public LoginApplicationService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    public String authenticateUser(UsernamePasswordLoginCommand command) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenService.generateToken((UserPrincipal) authentication.getPrincipal());
    }
}