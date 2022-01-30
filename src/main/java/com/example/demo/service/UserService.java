package com.example.demo.service;

import java.util.List;

import com.example.demo.beans.*;

import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<List<User>> getAllUsers();    
    public ResponseEntity<String> deleteUser(Long id);
    public ResponseEntity<String> saveUser(User user);
}
