package com.example.demo.service;

import com.example.demo.Component.MyUserDetails;
import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {


    public UserRepository userrepo;

    @Autowired
    public MyUserDetailsService(UserRepository userrepo) {
        this.userrepo=userrepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user=userrepo.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        return new MyUserDetails(user);
    }
    
}
