package com.example.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;


@Service
public class TokenServiceImpl implements TokenService {
    private HashMap<Long, String> hashMap = new HashMap<>();

    @Autowired
    public TokenServiceImpl() {
    }

    @Override
    public Token createToken(Long userid) {
        String tokenString = UUID.randomUUID().toString().replace("-", "");
        Token token = new Token(userid, tokenString);
        hashMap.put(userid, tokenString);
        return token;
    }

    @Override
    public boolean checkToken(Token token) {
        if (token == null)
            return false;
        String s = hashMap.get(token);
        if (s == null || !token.getToken().equals(s))
            return false;
        return true;
    }

    @Override
    public void deleteToken(Long userid) {
        hashMap.put(userid, null);
    }
}
