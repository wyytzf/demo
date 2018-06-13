package com.example.token;

public class Token {
    private Long userid;
    private String token;

    public Token(Long userid, String token) {
        this.userid = userid;
        this.token = token;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserid() {
        return userid;
    }

    public String getToken() {
        return token;
    }
}
