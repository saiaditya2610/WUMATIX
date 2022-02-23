package com.example.demo.configuration;



import com.example.demo.Component.OAuth2SuccessHandler;

import com.example.demo.Utility.JwtFilter;

import com.example.demo.service.MyOAuth2UserService;
import com.example.demo.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class WebConfiguration  extends WebSecurityConfigurerAdapter{

    @Autowired
    private JwtFilter jwtFilter;
 
    @Autowired
    public MyUserDetailsService userDetailsService;

    @Autowired
    public MyOAuth2UserService oauth2userservice;

    @Autowired
    OAuth2SuccessHandler successHandler;

    
    @Bean
    public AuthenticationProvider authenticationProvider()
    {

        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub

        
       
		
        http.cors().and().authorizeRequests()
        .antMatchers("/authenticate").permitAll()
        
        .antMatchers("/oauth2/**").permitAll()
        /*
        .antMatchers("/course").authenticated()
        .antMatchers("/styles.css","/back.jpeg","/static/**","/edit.png").permitAll()
        .antMatchers("/","/home","/Main").authenticated()
        .antMatchers("/add").hasAuthority("ADMIN")
        .antMatchers("/addCourse/**").hasAuthority("ADMIN")
        .antMatchers("/del/**").hasAuthority("ADMIN")
        .antMatchers("/edit/**").hasAuthority("ADMIN")
        .antMatchers("/delUser/**","/addUserDetails/**","/adduser.html").hasAuthority("ADMIN")
        */
        .anyRequest().authenticated()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //.and().oauth2Login().userInfoEndpoint().userService(oauth2userservice)
        //.and().successHandler(successHandler)
        .and().exceptionHandling().accessDeniedPage("/denied.html")
        .and().httpBasic().and().csrf().disable();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
    }
 

    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
/*
    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource src= new UrlBasedCorsConfigurationSource();
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        src.registerCorsConfiguration("/**", config);
        return new CorsFilter(src);
    }
*/

    
    
}
