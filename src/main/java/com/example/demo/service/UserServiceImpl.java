package com.example.demo.service;

import java.util.List;

import com.example.demo.beans.User;
import com.example.demo.exceptionhandler.CourseNotFound;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

 
    private UserRepository userrepo;

    @Autowired
    public UserServiceImpl(UserRepository userrepo) {
        this.userrepo = userrepo;
        
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        // TODO Auto-generated method stub
        return new ResponseEntity<>(userrepo.findAll(),HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        // TODO Auto-generated method stub
        if(!userrepo.existsById(id))
        {
            String message="Cannot Delete as Id:" + id + " not Found";
            throw new CourseNotFound(message);

        }
        userrepo.deleteById(id);
        return new ResponseEntity<>("Deleted Successfully with Id",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> saveUser(User user) {
        // TODO Auto-generated method stub

       
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userrepo.save(user);
        return new ResponseEntity<>("Added Successfully",HttpStatus.CREATED);

    }

   
    
}
