package com.example.security;

import com.example.user.SecurityUser;
import com.example.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private UserService userService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public TokenFilter(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String tokenHead = authorization.substring("Bearer ".length());
            String account = jwtTokenService.getAccountFromToken(tokenHead);
            if (account != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityUser user = (SecurityUser) userService.loadUserByUsername(account);
                if (jwtTokenService.validateToken(tokenHead, user)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    request.setAttribute("currentUser", user.getId());
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
