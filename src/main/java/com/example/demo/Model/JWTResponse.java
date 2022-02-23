package com.example.demo.Model;

public class JWTResponse{
    public String jwtToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JWTResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public JWTResponse() {
    }


}
