package com.example.demo.Component;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.beans.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    public String username;

    @Autowired
    private UserService userserv;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User user=(CustomOAuth2User) authentication.getPrincipal();
        username=user.getName();
        System.out.println(user.getEmail());
        List<User> Users=userserv.getMailId(user.getEmail());
        boolean flag=false;
        for(int i=0;i<Users.size();i++)
        {
            if(Users.get(i).getProvider().equals("GOOGLE"))
            {
                flag=true;
            }
        }
        if(flag==false)
        {
            User newuser=new User();
            newuser.setMail(user.getEmail());
            newuser.setUsername(user.getName());
            newuser.setRole("USER");
            newuser.setPassword("password");
            newuser.setProvider("GOOGLE");
            userserv.saveUser(newuser);

        }
     
        super.onAuthenticationSuccess(request, response, authentication);
    }

    public String getUsername()
    {
        return username;
    }

}