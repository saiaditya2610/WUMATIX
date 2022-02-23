package com.example.demo.service;

import com.example.demo.Component.CustomOAuth2User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;



@Service
public class MyOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserRepository userrepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User user=super.loadUser(userRequest);

        return new CustomOAuth2User(user);
    }




    
}
