package com.example.demo.configuration;


import com.example.demo.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebConfiguration  extends WebSecurityConfigurerAdapter{

 
    @Autowired
    public MyUserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider()
    {

        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub

        http.cors();
        http.authorizeRequests()
        .antMatchers("/styles.css","/back.jpeg","/static/**","/edit.png").permitAll()
        .antMatchers("/","/home","/Main").authenticated()
        .antMatchers("/add").hasAuthority("ADMIN")
        .antMatchers("/addCourse/**").hasAuthority("ADMIN")
        .antMatchers("/del/**").hasAuthority("ADMIN")
        .antMatchers("/edit/**").hasAuthority("ADMIN")
        .antMatchers("/delUser/**","/addUserDetails/**","/adduser.html").hasAuthority("ADMIN")
        .anyRequest().authenticated().and().formLogin()
        .and().exceptionHandling().accessDeniedPage("/denied.html")
        .and().httpBasic().and().csrf().disable();
    }
 

    


    
    
}
