package com.example.utils;

import com.example.user.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtTokenUtil implements Serializable {
    private final String secret = "12345678";


    //  过期 异常 catch
    // 如果快过期，并且用户还在线，则续期

    public String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + 5000 * 60 * 60);
        byte[] encodedKey = Base64.decodeBase64(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return Jwts.builder().addClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, key).compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        return generateToken(claims);
    }


    public Claims getClaimsFromToken(String token) {
        Claims claims;
        byte[] encodedKey = Base64.decodeBase64(secret);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

    public String getAccountFromToken(String token) {
        String account;
        Claims claims = getClaimsFromToken(token);
        account = claims.getSubject();
        return account;
    }

    public Boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }


    public String refreshToken(String Token) {
        String refreshToken;
        Claims claims = getClaimsFromToken(Token);
        claims.put("created", new Date());
        refreshToken = generateToken(claims);
        return refreshToken;
    }

    public Boolean validateToken(String Token, UserDetails userDetails) {
        SecurityUser user = (SecurityUser) userDetails;
        String account = getAccountFromToken(Token);
        return account.equals(user.getUsername()) && !isTokenExpired(Token);
    }


}
