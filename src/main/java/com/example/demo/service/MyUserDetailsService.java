package com.example.demo.service;

import java.util.List;

import com.example.demo.Component.MyUserDetails;
import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger log=LoggerFactory.getLogger(MyUserDetailsService.class);

    public UserRepository userrepo;

    @Autowired
    public MyUserDetailsService(UserRepository userrepo) {
        this.userrepo=userrepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("About to Authenicate");
        List<User> user=userrepo.findByMail(username);
        if(user.isEmpty())
        {
            log.error("User Not Found");
            throw new UsernameNotFoundException("User not found");
        }
        log.info("Success");

        return new MyUserDetails(user.get(0));
    }
    
}
