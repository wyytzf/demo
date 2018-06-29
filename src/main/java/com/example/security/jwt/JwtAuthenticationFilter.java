package com.example.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.core.context.SecurityContextHolder.createEmptyContext;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_HEADER = "Bearer ";


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new NegatedRequestMatcher(new OrRequestMatcher(new AntPathRequestMatcher("/login"), new AntPathRequestMatcher("/error"))));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String tokenString = extractToken(request);
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(null, tokenString, null));
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationString = request.getHeader(AUTHORIZATION);

        if (authorizationString == null || !authorizationString.startsWith(TOKEN_HEADER)) {
            throw new BadCredentialsException("Authorization header missing or with invalid format.");
        }

        return authorizationString.substring(TOKEN_HEADER.length(), authorizationString.length());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        SecurityContext context = createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException exception) throws IOException, ServletException {
        getFailureHandler().onAuthenticationFailure(request, response, exception);
    }

}
