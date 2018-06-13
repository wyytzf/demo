package com.example.token;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    public Token createToken(Long userid);

    public boolean checkToken(Token token);

    public void deleteToken(Long userid);
}
