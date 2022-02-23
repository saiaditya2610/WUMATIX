package com.example.demo.Model;

public class JWTRequest {
  


    public String username;
    public String password;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public JWTRequest() {
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public JWTRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
}
