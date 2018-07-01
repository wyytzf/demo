package com.example.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.web.context.request.RequestContextHolder.getRequestAttributes;


@Component
public class JwtTokenService implements Serializable {
    private final String secret = "12345678";

    public String generateToken(UserPrincipal userPrincipal) {

        byte[] encodedKey = Base64.decodeBase64(secret);
        Date expirationDate = new Date(System.currentTimeMillis() + 5000 * 60 * 60);
        Claims claims = Jwts.claims().setSubject(String.valueOf(userPrincipal.getId()));
        claims.put("NAME", userPrincipal.getUsername());
        claims.put("SCOPES", userPrincipal.getRole());

        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        String tokenString = Jwts
            .builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();
        updateResponseHeader(tokenString);
        return tokenString;
    }

    private void updateResponseHeader(String tokenString) {
        Optional.ofNullable((ServletRequestAttributes) getRequestAttributes())
            .map(ServletRequestAttributes::getResponse)
            .ifPresent(resp -> resp.setHeader(AUTHORIZATION, JwtAuthenticationFilter.TOKEN_HEADER + tokenString));
    }

    public JwtAuthenticationToken from(String jwtToken) {
        Claims claims = getClaimsFromToken(jwtToken);
        String role = (String) claims.get("SCOPES");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));
        UserPrincipal user = new UserPrincipal(Long.valueOf(claims.getSubject()), String.valueOf(claims.get("NAME")), role);
        JwtAuthenticationToken token = new JwtAuthenticationToken(user, null, grantedAuthorities);
        token.setAuthenticated(true);
        return token;
    }

    private Claims getClaimsFromToken(String token) {
        byte[] encodedKey = Base64.decodeBase64(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return Jwts
            .parser()
            .setSigningKey(key)
            .parseClaimsJws(token)
            .getBody();
    }
}
