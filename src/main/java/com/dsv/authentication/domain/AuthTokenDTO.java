package com.dsv.authentication.domain;


public class AuthTokenDTO {

    private String token;

    public AuthTokenDTO(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthTokenDTO{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
