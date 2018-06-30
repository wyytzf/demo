package com.example.security.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 8/27/17.
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtTokenService jwtTokenService;

    public JwtAuthenticationProvider(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        try {
            return jwtTokenService.from(authenticationToken.getJwtToken());
        } catch (Exception e) {
            throw new BadCredentialsException("登录信息失效.", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
